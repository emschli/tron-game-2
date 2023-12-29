package haw.vs.model.gamelogic.api;

import haw.vs.model.gamelogic.impl.GameStateProcessedHandler;

public class GameStateProcessedHandlerFactory {
    public static IGameStateProcessedHandler getGameStateProcessedHandler() {
        return new GameStateProcessedHandler();
    }
}
