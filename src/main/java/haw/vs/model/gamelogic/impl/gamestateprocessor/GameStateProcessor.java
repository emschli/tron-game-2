package haw.vs.model.gamelogic.impl.gamestateprocessor;

import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.impl.IWorkerPool;
import haw.vs.model.gamelogic.api.IGameStateProcessor;
import haw.vs.model.gamelogic.impl.IGameStateProcessedHandler;
import haw.vs.model.gamelogic.impl.WorkerPool;

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
