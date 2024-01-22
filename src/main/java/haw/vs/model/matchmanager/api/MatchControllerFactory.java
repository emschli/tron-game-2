package haw.vs.model.matchmanager.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.middleware.clientStub.api.ClientStubFactory;
import haw.vs.middleware.serverStub.impl.ServerStubFacade;
import haw.vs.model.matchmanager.impl.matchcontroller.MatchManager;
import haw.vs.model.matchmanager.impl.matchcontroller.MatchManagerAppStubConsumer;
import haw.vs.model.matchmanager.impl.matchcontroller.MatchManagerAppStubProvider;
import haw.vs.model.matchmanager.impl.mock.MockMatchController;
import haw.vs.model.matchmanager.impl.state.Matches;

public class MatchControllerFactory {
    public static IMatchController getMatchController(ComponentType forComponentType) {
        try {
            if (PropertiesHelper.isTest(ComponentType.MATCH_MANAGER)) {
                return new MockMatchController();
            }
            switch (PropertiesHelper.getAppType()) {
                case STANDALONE:
                    return new MatchManager(Matches.getInstance());
                case DISTRIBUTED:
                    if (forComponentType == ComponentType.MATCH_MANAGER) {
                        return new MatchManagerAppStubProvider(new ServerStubFacade(), new MatchManager(Matches.getInstance()));
                    } else {
                        return new MatchManagerAppStubConsumer(ClientStubFactory.getClientStub());
                    }
                default :
                    return new MockMatchController();
            }
        } catch (PropertiesException e) {
            throw new RuntimeException(e);
        }
    }
}
