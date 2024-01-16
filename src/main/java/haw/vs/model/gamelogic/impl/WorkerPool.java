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

//    public static void main(String[] args) {
//        WorkerPool wp = new WorkerPool();
//
//        // Thread that adds tasks to the WorkerPool
//        Thread taskAdderThread = new Thread(() -> {
//            // Simulate adding tasks to the WorkerPool at intervals
//            for (int i = 1; i <= 10; i++) {
//                Match match = new Match(i); // Replace with your Match constructor
//
//                wp.addTask(match);
//                System.out.println("Added Match_" + i + " to the worker pool.");
//
//                // Sleep for a short duration before adding the next task
//                try {
//                    Thread.sleep(1000); // Sleep for 1 second
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//
//            // Sleep for 5 seconds after adding all tasks to simulate no tasks being added
//            try {
//                Thread.sleep(5000); // Sleep for 5 seconds (no tasks added)
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        });
//
//        taskAdderThread.start();
//    }


}


