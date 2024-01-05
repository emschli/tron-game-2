package haw.vs.controller.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.impl.GameViewUpdate;
import haw.vs.controller.mock.MockGameViewUpdate;
import haw.vs.view.api.ViewFactory;

public class GameViewUpdateFactory {
    public static IGameViewUpdate getGameViewUpdate() {
        try {
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
        } catch (PropertiesException e) {
            throw new RuntimeException(e);
        }
    }
}
