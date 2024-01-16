package haw.vs.model.matchmanager.viewupdate;

import haw.vs.model.common.Match;
import haw.vs.model.matchmanager.state.Matches;
import haw.vs.model.matchmanager.tick.TickSummary;

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
            try {
                Match match = matches.getNextMatchForViewUpdate();
                matchUpdateHandler.updateView(match);
                TickSummary.addMatchesSentToView();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
