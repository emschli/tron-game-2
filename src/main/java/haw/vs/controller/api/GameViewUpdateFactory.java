package haw.vs.controller.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.impl.GameViewUpdate;
import haw.vs.controller.mock.MockGameViewUpdate;
import haw.vs.view.api.ViewFactory;
import haw.vs.view.mock.MockViewFacade;

public class GameViewUpdateFactory {
    public static IGameViewUpdate getGameViewUpdate() throws PropertiesException {

        if (PropertiesHelper.isTest(ComponentType.CONTROLLER)) {
            return new MockGameViewUpdate();
        }

        switch (PropertiesHelper.getAppType()) {
            case STANDALONE -> {
                return new GameViewUpdate(ViewFactory.getView());
            } default -> {
                return new MockGameViewUpdate();
            }
        }
    }
}
