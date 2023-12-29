package haw.vs.model.gamelogic.impl;

import haw.vs.common.properties.PropertiesException;
import haw.vs.model.matchmanager.api.GameStateUpdaterFactory;

public class GameStateProcessedHandlerFactory {
    public static IGameStateProcessedHandler getGameStateProcessedHandler() throws PropertiesException {
        return new GameStateProcessedHandler(GameStateUpdaterFactory.getGameStateUpdater());
    }
}
