package haw.vs.model.matchmanager.tick;

import haw.vs.model.matchmanager.state.Matches;

import java.util.concurrent.CountDownLatch;

public class TickThread implements Runnable {
    private final ITickHandler tickHandler;
    private final Matches matches;
    private final CountDownLatch initDoneCountDownLatch;
    private final CountDownLatch everybodyElseFinishedCountDownLatch;

    public TickThread(ITickHandler tickHandler, Matches matches, CountDownLatch initDoneCountDownLatch, CountDownLatch everybodyElseFinishedCountDownLatch) {
        this.tickHandler = tickHandler;
        this.matches = matches;
        this.initDoneCountDownLatch = initDoneCountDownLatch;
        this.everybodyElseFinishedCountDownLatch = everybodyElseFinishedCountDownLatch;
    }

    @Override
    public void run() {
        initDoneCountDownLatch.countDown();

        matches.updateLock.lock();
        try {
            everybodyElseFinishedCountDownLatch.await();
            while (true) {
                tickHandler.handleTick();
            }
        } catch (InterruptedException e) {

        }
    }
}
