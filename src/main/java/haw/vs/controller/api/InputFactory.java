package haw.vs.controller.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.impl.InputHandler;
import haw.vs.controller.mock.MockInput;
import haw.vs.model.matchmanager.api.MatchControllerFactory;

public class InputFactory {
    public static IInput getInput() throws PropertiesException {
        if (PropertiesHelper.isTest(ComponentType.CONTROLLER)) {
            return new MockInput();
        }

        switch (PropertiesHelper.getAppType()) {
            case STANDALONE -> {
                return new InputHandler(MatchControllerFactory.getMatchController());
            } default -> {
                return new MockInput();
            }
        }
    }
}
