package haw.vs.model.matchmanager;

public class TickThread implements Runnable {
    private ITickHandler tickHandler;
    private Thread gameUpdateThread;
    private Matches matches;

    public TickThread(ITickHandler tickHandler, Thread gameUpdateThread, Matches matches) {
        this.tickHandler = tickHandler;
        this.gameUpdateThread = gameUpdateThread;
        this.matches = matches;
    }

    @Override
    public void run() {
        matches.updateLock.lock();
        while (true) {
            try {
                tickHandler.handleTick(gameUpdateThread);
            } catch (InterruptedException e) {

            }
        }
    }
}
