package haw.vs.model.gamelogic.impl;

import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.IWorkerPool;
import haw.vs.model.gamelogic.IGameStateProcessedHandler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class WorkerPool implements IWorkerPool {

    private IGameStateProcessedHandler gameStateProcessedHandler;

    private ArrayBlockingQueue<Runnable> queue;

    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void addTask(Match match) {

    }

}
