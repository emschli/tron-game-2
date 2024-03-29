package haw.vs.view.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.api.InputFactory;
import haw.vs.middleware.clientStub.api.ClientStubFactory;
import haw.vs.middleware.serverStub.impl.ServerStubFacade;
import haw.vs.view.impl.*;
import haw.vs.view.impl.viewfacade.ViewFacade;
import haw.vs.view.impl.viewfacade.ViewFacadeAppStubConsumer;
import haw.vs.view.impl.viewfacade.ViewFacadeAppStubProvider;
import haw.vs.view.impl.mock.MockViewFacade;

/**
 * ViewFactory offers:
 * - IViewFacade, depending on the case STANDALONE/DISTRIBUTED with different Stubbs
 * - IInputHandler
 */
public class ViewFactory {
    public static IViewFacade getView(ComponentType isComp) {
        try {
            if (PropertiesHelper.isTest(ComponentType.VIEW)) {
                return new MockViewFacade();
            }

            switch (PropertiesHelper.getAppType()) {
                case STANDALONE : return new ViewFacade();
                case DISTRIBUTED:
                    if (isComp == ComponentType.VIEW) {
                        return new ViewFacadeAppStubProvider(new ServerStubFacade(), new ViewFacade());
                    } else {
                        return new ViewFacadeAppStubConsumer(ClientStubFactory.getClientStub());
                    }
                default: return new MockViewFacade();
            }
        } catch (PropertiesException e) {
            throw new RuntimeException(e);
        }

    }

    public static IPlayerInputHandler getInputHandler(){
        return new PlayerInputHandler(InputFactory.getInput(ComponentType.VIEW));
    }
}
