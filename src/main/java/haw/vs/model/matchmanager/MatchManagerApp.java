package haw.vs.model.matchmanager;

import haw.vs.common.ICallee;
import haw.vs.common.properties.AppType;
import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.model.matchmanager.api.GameStateUpdaterFactory;
import haw.vs.model.matchmanager.api.MatchControllerFactory;
import haw.vs.model.matchmanager.state.Matches;
import haw.vs.model.matchmanager.tick.TickHandlerFactory;
import haw.vs.model.matchmanager.tick.TickThread;
import haw.vs.model.matchmanager.viewupdate.GameUpdateThread;
import haw.vs.model.matchmanager.viewupdate.MatchUpdateHandlerFactory;
import haw.vs.model.matchmanager.viewupdate.MenuUpdateThread;
import haw.vs.view.api.IComponentApp;

import java.util.concurrent.CountDownLatch;

public class MatchManagerApp implements IComponentApp {
    @Override
    public void startApp(CountDownLatch viewStartedCountDownLatch, CountDownLatch everyBodyElseStartedCountDownLatch) {
        try {
            viewStartedCountDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        CountDownLatch initDoneCountDownLatch = new CountDownLatch(3);

        MenuUpdateThread menuUpdateThread = new MenuUpdateThread(Matches.getInstance(),
                MatchUpdateHandlerFactory.getMatchUpdateHandler(),
                initDoneCountDownLatch,
                everyBodyElseStartedCountDownLatch);
        Thread menuUpdateThreadThread = new Thread(menuUpdateThread, "MenuUpdateThread");

        GameUpdateThread gameUpdateThread = new GameUpdateThread(MatchUpdateHandlerFactory.getMatchUpdateHandler(),
                Matches.getInstance(),
                initDoneCountDownLatch,
                everyBodyElseStartedCountDownLatch);
        Thread gameUpdateThreadThread = new Thread(gameUpdateThread, "GameUpdateThread");

        TickThread tickThread = new TickThread(TickHandlerFactory.getTickHandler(),
                gameUpdateThreadThread,
                Matches.getInstance(),
                initDoneCountDownLatch,
                everyBodyElseStartedCountDownLatch);
        Thread tickThreadThread = new Thread(tickThread, "TickThread");

        menuUpdateThreadThread.start();
        gameUpdateThreadThread.start();
        tickThreadThread.start();

        try {
            initDoneCountDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            if (PropertiesHelper.getAppType() == AppType.DISTRIBUTED) {
                ICallee matchController = (ICallee) MatchControllerFactory.getMatchController(ComponentType.MATCH_MANAGER);
                ICallee gameStateUpdater = (ICallee) GameStateUpdaterFactory.getGameStateUpdater(ComponentType.MATCH_MANAGER);
                matchController.register();
                gameStateUpdater.register();
            }
        } catch (PropertiesException e) {
            throw new RuntimeException(e);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        }

        everyBodyElseStartedCountDownLatch.countDown();
    }
}
