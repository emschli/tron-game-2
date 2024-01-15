package haw.vs.model.gamelogic.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.middleware.clientStub.api.ClientStubFactory;
import haw.vs.middleware.serverStub.impl.ServerStubFacade;
import haw.vs.model.gamelogic.impl.GameStateProcessedHandlerFactory;
import haw.vs.model.gamelogic.impl.GameStateProcessor;
import haw.vs.model.gamelogic.impl.GameStateProcessorAppStubConsumer;
import haw.vs.model.gamelogic.impl.GameStateProcessorAppStubProvider;
import haw.vs.model.gamelogic.mock.MockGameStateProcessor;


public class GameStateProcessorFactory {
    public static IGameStateProcessor getGameStateProcessor(ComponentType forComponentType) {
        try {
            if (PropertiesHelper.isTest(ComponentType.GAME_LOGIC)) {
                return new MockGameStateProcessor();
            }
            switch (PropertiesHelper.getAppType()) {
                case STANDALONE:
                    return new GameStateProcessor(GameStateProcessedHandlerFactory.getGameStateProcessedHandler());
                case DISTRIBUTED:
                    if (forComponentType == ComponentType.GAME_LOGIC) {
                        return new GameStateProcessorAppStubProvider(new ServerStubFacade(), new GameStateProcessor(GameStateProcessedHandlerFactory.getGameStateProcessedHandler()));
                    } else {
                        return new GameStateProcessorAppStubConsumer(ClientStubFactory.getClientStub());
                    }
                default:
                    return new MockGameStateProcessor();
            }
        } catch (PropertiesException e) {
            throw new RuntimeException(e); //✅ no props no fun
        }
    }
}
