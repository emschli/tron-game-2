package haw.vs.model.gamelogic.impl;

import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.model.common.Match;

import java.util.concurrent.*;

public class WorkerPool implements IWorkerPool {

    private static int THREAD_COUNT;
    static {
        String countString = null;
        try {
            countString = PropertiesHelper.getProperty("game_logic_thread_count");
        } catch (PropertiesException e) {

        }

        int count = 1;

        try {
            count = Integer.parseInt(countString);
        } catch (NumberFormatException e) {

        }

        THREAD_COUNT = count;
    }

    private ArrayBlockingQueue<Runnable> queue;
    private ThreadPoolExecutor threadPoolExecutor;
    private GameLogic gameLogic;


    public WorkerPool(IGameStateProcessedHandler gameStateProcessedHandler) {
        this.gameLogic = new GameLogic(gameStateProcessedHandler);
        this.queue = new ArrayBlockingQueue<Runnable>(5);
        this.threadPoolExecutor = new ThreadPoolExecutor(
                THREAD_COUNT,
                THREAD_COUNT+3,
                5000,
                TimeUnit.MILLISECONDS,
                queue);
        threadPoolExecutor.prestartAllCoreThreads();

    }

    /**
     * Adds a match to a queue. Items in the queue are processed to the GameLocic.
     * @param match
     */
    @Override
    public void addTask(Match match) {
        // wrap match in a runnable
        Runnable task = () -> {
            gameLogic.processMatchGameLogic(match);
        };
        // add runnable to Queue
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}


