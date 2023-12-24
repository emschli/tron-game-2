package haw.vs.model.gamelogic.api;

import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.IGameStateProcessor;
import haw.vs.model.gamelogic.IWorkerPool;
import haw.vs.model.gamelogic.impl.WorkerPool;

public class GameStateProcessor implements IGameStateProcessor {

    private final IWorkerPool workerPool;

    public GameStateProcessor() {
        this.workerPool = new WorkerPool();
    }

    @Override
    public void addTask(Match match) {
        workerPool.addTask(match);
    }
}
