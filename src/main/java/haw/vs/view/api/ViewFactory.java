package haw.vs.view.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.api.InputFactory;
import haw.vs.view.mock.MockViewFacade;

public class ViewFactory {
    public static IViewFacade getView() {
        try {
            if (PropertiesHelper.isTest(ComponentType.VIEW)) {
                return new MockViewFacade();
            }

            switch (PropertiesHelper.getAppType()) {
                case STANDALONE : return new ViewFacade();
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
