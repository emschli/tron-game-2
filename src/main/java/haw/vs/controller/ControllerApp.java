package haw.vs.controller;

import haw.vs.common.ICallee;
import haw.vs.common.properties.AppType;
import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.api.GameViewUpdateFactory;
import haw.vs.controller.api.InputFactory;
import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.view.api.IComponentApp;

import java.util.concurrent.CountDownLatch;

public class ControllerApp implements IComponentApp {
    @Override
    public void startApp(CountDownLatch viewStartedCountDownLatch, CountDownLatch everyBodyElseStartedCountDownLatch) throws MethodNameAlreadyExistsException {
        try {
            if (PropertiesHelper.getAppType() == AppType.DISTRIBUTED) {
                viewStartedCountDownLatch.await();

                ICallee gameViewUpdater = (ICallee) GameViewUpdateFactory.getGameViewUpdate(ComponentType.CONTROLLER);
                ICallee input = (ICallee) InputFactory.getInput(ComponentType.CONTROLLER);
                gameViewUpdater.register();
                input.register();

                everyBodyElseStartedCountDownLatch.countDown();
            }
        } catch (PropertiesException | NameServiceException e) {
            throw new RuntimeException(e); //✅ - no props, no name service, no fun
        } catch (InterruptedException e) {
            throw new RuntimeException(e); //🧵
        }
    }
}
