package haw.vs.controller.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.appstub.InputAppStubConsumer;
import haw.vs.controller.appstub.InputAppStubProvider;
import haw.vs.controller.impl.InputHandler;
import haw.vs.controller.mock.MockInput;
import haw.vs.middleware.clientStub.api.ClientStubFactory;
import haw.vs.middleware.serverStub.impl.ServerStubFacade;
import haw.vs.model.matchmanager.api.MatchControllerFactory;

public class InputFactory {
    public static IInput getInput(ComponentType isComp) {
        try {
            if (PropertiesHelper.isTest(ComponentType.CONTROLLER)) {
                return new MockInput();
            }

            switch (PropertiesHelper.getAppType()) {
                case STANDALONE:
                    return new InputHandler(MatchControllerFactory.getMatchController(ComponentType.CONTROLLER));
                case DISTRIBUTED:
                    if (isComp == ComponentType.CONTROLLER) {
                        return new InputAppStubProvider(new ServerStubFacade(), new InputHandler(MatchControllerFactory.getMatchController(ComponentType.CONTROLLER)));
                    } else {
                        return new InputAppStubConsumer(ClientStubFactory.getClientStub());
                    }
                default:
                    return new MockInput();
            }

        } catch (PropertiesException e) {
            throw new RuntimeException(e); //✅ no props no fun
        }
    }
}
