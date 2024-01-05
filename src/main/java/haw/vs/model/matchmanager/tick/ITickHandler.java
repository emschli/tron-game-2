package haw.vs.model.matchmanager.tick;

public interface ITickHandler {
    void handleTick(Thread gameUpdateThread) throws InterruptedException;
}
