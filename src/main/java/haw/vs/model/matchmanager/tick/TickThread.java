package haw.vs.model.matchmanager.tick;

import haw.vs.model.matchmanager.state.Matches;

public class TickThread implements Runnable {
    private final ITickHandler tickHandler;
    private final Thread gameUpdateThread;
    private final Matches matches;

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
