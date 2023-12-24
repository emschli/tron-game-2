package haw.vs.model.gamelogic.impl;

import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.IWorkerPool;
import haw.vs.model.gamelogic.IGameStateProcessedHandler;
import haw.vs.model.gamelogic.api.GameLogicFactory;

import java.util.Objects;
import java.util.concurrent.*;

public class WorkerPool implements IWorkerPool {

    private IGameStateProcessedHandler gameStateProcessedHandler;

    private ArrayBlockingQueue<Runnable> queue;

    private ThreadPoolExecutor threadPoolExecutor;

    private GameLogic gameLogic;

    private ScheduledExecutorService scheduledExecutor;

    public WorkerPool() {
        GameLogicFactory glf = new GameLogicFactory();
        this.gameLogic = new GameLogic();
        this.gameStateProcessedHandler = glf.getGameStateProcessedHandler();
        this.queue = new ArrayBlockingQueue<Runnable>(5);
        this.threadPoolExecutor = new ThreadPoolExecutor(
                3,
                5,
                5000,
                TimeUnit.MILLISECONDS,
                queue);
        threadPoolExecutor.prestartAllCoreThreads();

        // ScheduledExecutorService to check for inactivity and shutdown if no tasks added
        this.scheduledExecutor = Executors.newScheduledThreadPool(1);
        scheduledExecutor.scheduleWithFixedDelay(() -> {
            if (queue.isEmpty()) {
                System.out.println("No tasks added for 15 seconds. Shutting down executor...");
                shutdownExecutor();
            }
        }, 15, 15, TimeUnit.SECONDS);

    }

    @Override
    public void addTask(Match match) {
        // wrap match in a runnable
        Runnable task = () -> {
            gameLogic.processMatch(match);
            gameStateProcessedHandler.updateMatch(match);
        };
        // add runnable to Queue
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void shutdownExecutor() {
        threadPoolExecutor.shutdown();
        scheduledExecutor.shutdown();
    }

    public static void main(String[] args) {
        WorkerPool wp = new WorkerPool();


        // Thread that adds tasks to the WorkerPool
        Thread taskAdderThread = new Thread(() -> {
            // Simulate adding tasks to the WorkerPool at intervals
            for (int i = 1; i <= 10; i++) {
                Match match = new Match(); // Replace with your Match constructor

                wp.addTask(match);
                System.out.println("Added Match_" + i + " to the worker pool.");

                // Sleep for a short duration before adding the next task (for demonstration purposes)
                try {
                    Thread.sleep(2000); // Sleep for 2 seconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Sleep for 15 seconds after adding all tasks to simulate no tasks being added
            try {
                Thread.sleep(15000); // Sleep for 15 seconds (no tasks added)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        taskAdderThread.start();
    }


}


