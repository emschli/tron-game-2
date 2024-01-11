package haw.vs.controller.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.appstub.GameViewUpdateAppStubConsumer;
import haw.vs.controller.appstub.GameViewUpdateAppStubProvider;
import haw.vs.controller.impl.GameViewUpdate;
import haw.vs.controller.mock.MockGameViewUpdate;
import haw.vs.middleware.clientStub.api.ClientStubFactory;
import haw.vs.middleware.serverStub.impl.ServerStubFacade;
import haw.vs.view.api.ViewFactory;

public class GameViewUpdateFactory {
    public static IGameViewUpdate getGameViewUpdate(ComponentType isComp) {
        try {
            if (PropertiesHelper.isTest(ComponentType.CONTROLLER)) {
                return new MockGameViewUpdate();
            }

            switch (PropertiesHelper.getAppType()) {
                case STANDALONE:
                    return new GameViewUpdate(ViewFactory.getView(ComponentType.CONTROLLER));
                case DISTRIBUTED:
                    if (isComp == ComponentType.CONTROLLER) {
                        return new GameViewUpdateAppStubProvider(new ServerStubFacade(), new GameViewUpdate(ViewFactory.getView(ComponentType.CONTROLLER)));
                    } else {
                        return new GameViewUpdateAppStubConsumer(ClientStubFactory.getClientStub());
                    }
                default:
                    return new MockGameViewUpdate();
            }
        } catch (PropertiesException e) {
            throw new RuntimeException(e);
        }
    }
}
