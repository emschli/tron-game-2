package haw.vs.model.matchmanager;

import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.api.IGameStateProcessor;

public class TickHandler implements ITickHandler {
    private Matches matches;
    private IGameStateProcessor gameStateProcessor;

    private static int TICKS_PER_SECOND = 30;
    private static long TICK_LENGTH = 1000 / TICKS_PER_SECOND;
    private static long INPUT_INTERVAL = TICK_LENGTH / 2;
    private static float MARGIN_PERCENTAGE = 0.2f;
    private static long MARGIN = (long) (INPUT_INTERVAL * MARGIN_PERCENTAGE);
    private static long COMPUTATION_INTERVAL = INPUT_INTERVAL - MARGIN;

    public TickHandler(Matches matches, IGameStateProcessor gameStateProcessor) {
        this.matches = matches;
        this.gameStateProcessor = gameStateProcessor;
    }

    @Override
    public void handleTick(Thread gameUpdateThread) throws InterruptedException {
        Thread.sleep(INPUT_INTERVAL); //allow input for first half of tick

        long startTime = System.currentTimeMillis();
        matches.inputLock.lock(); //no more input
        matches.updateLock.unlock();
        matches.viewUpdateLock.lock();
        matches.startWork.signalAll();
        matches.viewUpdateLock.unlock();
        for (Match match : matches.getRunningMatches()) {
            gameStateProcessor.addTask(match);
        }
        long elapsedTime = System.currentTimeMillis() - startTime;

        //Sleep for the rest of the computation Interval
        Thread.sleep(COMPUTATION_INTERVAL - elapsedTime);

        long startTime2 = System.currentTimeMillis();
        matches.updateLock.lock();
        gameUpdateThread.interrupt();

        matches.viewUpdateLock.lock();
        while (matches.hasNextMatchForViewUpdate()) {
            matches.cleanupDone.await();
        }
        matches.viewUpdateLock.unlock();
        long elapsedTime2 = System.currentTimeMillis() - startTime2;
        Thread.sleep(MARGIN - elapsedTime2); //Sleep for rest of Margin

        matches.inputLock.unlock(); // allow Inputs again
    }
}
