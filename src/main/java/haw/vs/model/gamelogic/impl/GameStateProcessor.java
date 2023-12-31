package haw.vs.model.gamelogic.impl;

import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.IWorkerPool;
import haw.vs.model.gamelogic.api.IGameStateProcessor;

public class GameStateProcessor implements IGameStateProcessor {

    private final IWorkerPool workerPool;

    public GameStateProcessor(IGameStateProcessedHandler gameStateProcessedHandler) {
        this.workerPool = new WorkerPool(gameStateProcessedHandler);
    }

    @Override
    public void addTask(Match match) {
        workerPool.addTask(match);
    }
}
