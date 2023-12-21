package haw.vs.model.matchmanager;

public interface ITickHandler {
    void handleTick(Thread gameUpdateThread) throws InterruptedException;
}
