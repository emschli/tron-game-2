package haw.vs.model.gamelogic.impl;

import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.IWorkerPool;

import java.util.concurrent.*;

public class WorkerPool implements IWorkerPool {

    private ArrayBlockingQueue<Runnable> queue;
    private ThreadPoolExecutor threadPoolExecutor;
    private GameLogic gameLogic;


    public WorkerPool(IGameStateProcessedHandler gameStateProcessedHandler) {
        this.gameLogic = new GameLogic(gameStateProcessedHandler);
        this.queue = new ArrayBlockingQueue<Runnable>(5);
        this.threadPoolExecutor = new ThreadPoolExecutor(
                3,
                5,
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


