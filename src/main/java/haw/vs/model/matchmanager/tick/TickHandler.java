package haw.vs.model.matchmanager.tick;

import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.api.IGameStateProcessor;
import haw.vs.model.matchmanager.state.Matches;

public class TickHandler implements ITickHandler {
    private final Matches matches;
    private final IGameStateProcessor gameStateProcessor;

    private static final int TICKS_PER_SECOND = 10;
    private static final long TICK_LENGTH = 1000 / TICKS_PER_SECOND;
    private static final long INPUT_INTERVAL = TICK_LENGTH / 2;
    private static final float MARGIN_PERCENTAGE = 0.2f;
    private static final long MARGIN = (long) (INPUT_INTERVAL * MARGIN_PERCENTAGE);
    private static final long COMPUTATION_INTERVAL = INPUT_INTERVAL - MARGIN;

    public TickHandler(Matches matches, IGameStateProcessor gameStateProcessor) {
        this.matches = matches;
        this.gameStateProcessor = gameStateProcessor;
    }

    @Override
    public void handleTick() throws InterruptedException {
        //long tickStart = System.nanoTime();
        TickCounter.incrementTickCounter();
        Thread.sleep(INPUT_INTERVAL); //allow input for first half of tick

        long startTime = System.currentTimeMillis();
        matches.inputLock.lock(); //no more input
        matches.updateLock.unlock();

        for (Match match : matches.getRunningMatches()) {
            Match matchCopy = match.copy();
            matchCopy.setTickStamp(TickCounter.getTickCounter());
            gameStateProcessor.addTask(matchCopy);
            TickSummary.addMatchesSentToGameLogic();
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        //Sleep for the rest of the computation Interval
        long sleepInterval = COMPUTATION_INTERVAL - elapsedTime;
        if (sleepInterval >= 0) {
            Thread.sleep(sleepInterval);
        } else {
            System.err.printf("Sending of Matches took too long! +%s ms\n", Math.abs(sleepInterval));
        }

        long startTime2 = System.currentTimeMillis();
        matches.updateLock.lock();
        long elapsedTime2 = System.currentTimeMillis() - startTime2;
        long sleepInterval2 = MARGIN - elapsedTime2;
        if (sleepInterval2 >= 0) {
            Thread.sleep(sleepInterval2); //Sleep for rest of Margin
        } else {
            System.err.printf("Computation Took too long! + %s ms\n", Math.abs(sleepInterval2));
        }

        // log tick summary
        if(TickSummary.anythingHappened()) {
            System.out.println(TickSummary.getTickSummaryMessage());
            TickSummary.clearAll();
        }

        matches.inputLock.unlock(); // allow Inputs again
    }
}
