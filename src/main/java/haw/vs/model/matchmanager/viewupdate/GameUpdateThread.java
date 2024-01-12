package haw.vs.model.matchmanager.viewupdate;

import haw.vs.model.common.Match;
import haw.vs.model.matchmanager.state.Matches;

import java.util.concurrent.CountDownLatch;

public class GameUpdateThread implements Runnable {
    private final IMatchUpdateHandler matchUpdateHandler;
    private final Matches matches;
    private final CountDownLatch initDoneCountDownLatch;
    private final CountDownLatch everybodyElseFinishedCountDownLatch;

    public GameUpdateThread(IMatchUpdateHandler matchUpdateHandler, Matches matches, CountDownLatch initDoneCountDownLatch, CountDownLatch everybodyElseFinishedCountDownLatch) {
        this.matchUpdateHandler = matchUpdateHandler;
        this.matches = matches;
        this.initDoneCountDownLatch = initDoneCountDownLatch;
        this.everybodyElseFinishedCountDownLatch = everybodyElseFinishedCountDownLatch;
    }

    @Override
    public void run() {
        initDoneCountDownLatch.countDown();

        try {
            everybodyElseFinishedCountDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
//                    Thread.currentThread().interrupt(); // clear interrupted flag //TODO: ok like this?
                }
            }

            //cleanup done -> tell tick thread
            matches.cleanupDone.signalAll();
            matches.viewUpdateLock.unlock();
        }
    }
}
