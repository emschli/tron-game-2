package haw.vs.common;

import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;

import java.util.concurrent.CountDownLatch;

public interface IComponentApp {
    public void startApp(CountDownLatch viewStartedCountDownLatch, CountDownLatch everyBodyElseStartedCountDownLatch) throws MethodNameAlreadyExistsException;
}