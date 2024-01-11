package haw.vs.view.api;

import java.util.concurrent.CountDownLatch;

public interface IComponentApp {
    public void startApp(CountDownLatch viewStartedCountDownLatch, CountDownLatch everyBodyElseStartedCountDownLatch);
}