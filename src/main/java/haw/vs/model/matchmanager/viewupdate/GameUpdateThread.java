package haw.vs.model.matchmanager.viewupdate;

import haw.vs.model.common.Match;
import haw.vs.model.matchmanager.state.Matches;

public class GameUpdateThread implements Runnable {
    private final IMatchUpdateHandler matchUpdateHandler;
    private final Matches matches;

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
