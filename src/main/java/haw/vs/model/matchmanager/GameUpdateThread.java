package haw.vs.model.matchmanager;

import haw.vs.model.common.Match;

public class GameUpdateThread implements Runnable {
    IMatchUpdateHandler matchUpdateHandler;
    Matches matches;

    public GameUpdateThread(IMatchUpdateHandler matchUpdateHandler, Matches matches) {
        this.matchUpdateHandler = matchUpdateHandler;
        this.matches = matches;
    }

    @Override
    public void run() {
        while (true) {
            matches.viewUpdateLock.lock();
            try {
                matches.startWork.await();

                while (!Thread.interrupted()) {
                    Match match = matches.getNextMatchForViewUpdate();
                    matchUpdateHandler.updateView(match);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // clear interrupted flag
            }

            //Interrupt came in -> time to clean up
            while (matches.hasNextMatchForViewUpdate()) {
                try {
                    Match match = matches.getNextMatchForViewUpdate();
                    matchUpdateHandler.updateView(match);
                } catch (InterruptedException e) {

                }
            }

            //cleanup done -> tell tick thread
            matches.cleanupDone.signalAll();
            matches.viewUpdateLock.unlock();
        }
    }
}
