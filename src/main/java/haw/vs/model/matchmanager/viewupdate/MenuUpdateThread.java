package haw.vs.model.matchmanager.viewupdate;

import haw.vs.model.matchmanager.state.Matches;
import haw.vs.model.matchmanager.state.MenuEvent;

import java.util.concurrent.CountDownLatch;

public class MenuUpdateThread implements Runnable {
    private final Matches matches;
    private final IMatchUpdateHandler matchUpdateHandler;

    private final CountDownLatch initDoneCountDownLatch;

    private final CountDownLatch everybodyElseFinishedCountDownLatch;


    public MenuUpdateThread(Matches matches, IMatchUpdateHandler matchUpdateHandler, CountDownLatch initDoneCountDownLatch, CountDownLatch everybodyElseFinishedCountDownLatch) {
        this.matches = matches;
        this.matchUpdateHandler = matchUpdateHandler;
        this.initDoneCountDownLatch = initDoneCountDownLatch;
        this.everybodyElseFinishedCountDownLatch = everybodyElseFinishedCountDownLatch;
    }

    @Override
    public void run() {
        initDoneCountDownLatch.countDown();

        try {
            everybodyElseFinishedCountDownLatch.await();
            while (true) {
                MenuEvent event = matches.getNextMenuEvent();
                matchUpdateHandler.updateView(event);
            }
        } catch (InterruptedException e) {

        }
    }
}
