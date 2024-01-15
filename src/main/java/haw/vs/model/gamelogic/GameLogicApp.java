package haw.vs.model.gamelogic;

import haw.vs.common.ICallee;
import haw.vs.common.properties.AppType;
import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.model.gamelogic.api.GameStateProcessorFactory;
import haw.vs.view.api.IComponentApp;

import java.util.concurrent.CountDownLatch;

public class GameLogicApp implements IComponentApp {
    @Override
    public void startApp(CountDownLatch viewStartedCountDownLatch, CountDownLatch everyBodyElseStartedCountDownLatch) throws MethodNameAlreadyExistsException {
        try {
            if (PropertiesHelper.getAppType() == AppType.DISTRIBUTED) {
                viewStartedCountDownLatch.await();
                ICallee gameStateProcessor = (ICallee) GameStateProcessorFactory.getGameStateProcessor(ComponentType.GAME_LOGIC);
                gameStateProcessor.register();

                everyBodyElseStartedCountDownLatch.countDown();
            }
        } catch (PropertiesException | NameServiceException e) {
            throw new RuntimeException(e); //✅ - no props, no name service, no fun
        } catch (InterruptedException e) {
            throw new RuntimeException(e); //🧵
        }
    }
}
