package haw.vs.model.gamelogic.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.model.gamelogic.impl.GameStateProcessedHandlerFactory;
import haw.vs.model.gamelogic.impl.GameStateProcessor;
import haw.vs.model.gamelogic.mock.MockGameStateProcessor;


public class GameStateProcessorFactory {
    public static IGameStateProcessor getGameStateProcessor() {
        try {
            if (PropertiesHelper.isTest(ComponentType.GAME_LOGIC)) {
                return new MockGameStateProcessor();
            }
            switch (PropertiesHelper.getAppType()) {
                case STANDALONE:
                    return new GameStateProcessor(GameStateProcessedHandlerFactory.getGameStateProcessedHandler());
                default:
                    return new MockGameStateProcessor();
            }
        } catch (PropertiesException e) {
            throw new RuntimeException(e);
        }
    }
}
