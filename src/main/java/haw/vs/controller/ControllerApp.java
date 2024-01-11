package haw.vs.controller;

import haw.vs.common.ICallee;
import haw.vs.common.properties.AppType;
import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.api.GameViewUpdateFactory;
import haw.vs.controller.api.InputFactory;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

public class ControllerApp {
    public void startApp() {
        try {
            if (PropertiesHelper.getAppType() == AppType.DISTRIBUTED) {
                ICallee gameViewUpdater = (ICallee) GameViewUpdateFactory.getGameViewUpdate(ComponentType.CONTROLLER);
                ICallee input = (ICallee) InputFactory.getInput(ComponentType.CONTROLLER);
                gameViewUpdater.register();
                input.register();
            }
        } catch (PropertiesException | NameServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
