package haw.vs.model.matchmanager.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.middleware.clientStub.api.ClientStubFactory;
import haw.vs.middleware.serverStub.impl.ServerStubFacade;
import haw.vs.model.matchmanager.gamestateupdater.GameStateUpdater;
import haw.vs.model.matchmanager.gamestateupdater.GameStateUpdaterAppStubConsumer;
import haw.vs.model.matchmanager.gamestateupdater.GameStateUpdaterAppStubProvider;
import haw.vs.model.matchmanager.mock.MockGameStateUpdater;
import haw.vs.model.matchmanager.state.Matches;

public class GameStateUpdaterFactory {
    public static IGameStateUpdater getGameStateUpdater(ComponentType forComponentType) {
        try {
            if (PropertiesHelper.isTest(ComponentType.MATCH_MANAGER)) {
                return new MockGameStateUpdater();
            }

            switch (PropertiesHelper.getAppType()) {
                case STANDALONE:
                    return new GameStateUpdater(Matches.getInstance());
                case DISTRIBUTED:
                    if (forComponentType == ComponentType.MATCH_MANAGER) {
                        return new GameStateUpdaterAppStubProvider(new ServerStubFacade(), new GameStateUpdater(Matches.getInstance()));
                    } else {
                        return new GameStateUpdaterAppStubConsumer(ClientStubFactory.getClientStub());
                    }
                default:
                    return new MockGameStateUpdater();
            }
        } catch (PropertiesException e) {
            throw new RuntimeException(e); //✅ no props no fun
        }
    }
}
