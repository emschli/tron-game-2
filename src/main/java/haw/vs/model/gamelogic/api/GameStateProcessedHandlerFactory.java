package haw.vs.model.gamelogic.api;

import haw.vs.model.gamelogic.IGameStateProcessedHandler;
import haw.vs.model.gamelogic.impl.GameStateProcessedHandler;

public class GameStateProcessedHandlerFactory {
    public IGameStateProcessedHandler getGameStateProcessedHandler() {
        return new GameStateProcessedHandler();
    }
}
