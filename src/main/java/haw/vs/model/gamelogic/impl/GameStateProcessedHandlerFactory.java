package haw.vs.model.gamelogic.impl;

import haw.vs.common.properties.ComponentType;
import haw.vs.model.matchmanager.api.GameStateUpdaterFactory;

public class GameStateProcessedHandlerFactory {
    public static IGameStateProcessedHandler getGameStateProcessedHandler() {
        return new GameStateProcessedHandler(GameStateUpdaterFactory.getGameStateUpdater(ComponentType.GAME_LOGIC));
    }
}
