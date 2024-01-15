package haw.vs;

import haw.vs.common.properties.AppType;
import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.ControllerApp;
import haw.vs.middleware.MiddlewareApp;
import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;
import haw.vs.model.gamelogic.GameLogicApp;
import haw.vs.model.matchmanager.MatchManagerApp;
import haw.vs.view.api.IComponentApp;
import haw.vs.view.api.ViewApp;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        try {
            AppType appType = PropertiesHelper.getAppType();

            CountDownLatch viewHasStartedCountDownLatch;
            CountDownLatch everbodyElseHasStartedCountDownLatch;

            switch (appType) {
                case STANDALONE:
                    IComponentApp viewApp = new ViewApp();
                    MatchManagerApp matchManagerApp = new MatchManagerApp();

                    viewHasStartedCountDownLatch = new CountDownLatch(1);
                    everbodyElseHasStartedCountDownLatch = new CountDownLatch(1);
                    viewApp.startApp(viewHasStartedCountDownLatch, everbodyElseHasStartedCountDownLatch);
                    matchManagerApp.startApp(viewHasStartedCountDownLatch, everbodyElseHasStartedCountDownLatch);
                    break;
                // Add other cases if needed
                case DISTRIBUTED:
                    MiddlewareApp middlewareApp = new MiddlewareApp();
                    middlewareApp.startApp();

                    List<ComponentType> componentsToBeStarted = PropertiesHelper.getAllComponents();
                    int componentCountWithoutView;
                    if (componentsToBeStarted.contains(ComponentType.VIEW)) {
                        componentCountWithoutView = componentsToBeStarted.size() - 1;
                        viewHasStartedCountDownLatch = new CountDownLatch(1);
                    } else {
                        componentCountWithoutView = componentsToBeStarted.size();
                        viewHasStartedCountDownLatch = new CountDownLatch(0);
                    }
                    everbodyElseHasStartedCountDownLatch = new CountDownLatch(componentCountWithoutView);

                    for (ComponentType componentType : componentsToBeStarted) {
                        switch (componentType) {
                            case VIEW:
                                new ViewApp().startApp(viewHasStartedCountDownLatch, everbodyElseHasStartedCountDownLatch);
                                break;
                            case CONTROLLER:
                                new ControllerApp().startApp(viewHasStartedCountDownLatch, everbodyElseHasStartedCountDownLatch);
                                break;
                            case MATCH_MANAGER:
                                new MatchManagerApp().startApp(viewHasStartedCountDownLatch, everbodyElseHasStartedCountDownLatch);
                                break;
                            case GAME_LOGIC:
                                new GameLogicApp().startApp(viewHasStartedCountDownLatch, everbodyElseHasStartedCountDownLatch);
                        }
                    }
                    break;
                default:
                    throw new RuntimeException("Invalid AppType"); //✅ - has to be one of given apptypes
            }
        } catch (PropertiesException e) {
            throw new RuntimeException(e); //✅ - no props no fun
        } catch (MethodNameAlreadyExistsException e) {
            throw new RuntimeException(e); //✅ - name-service can't work with two same method names
        }

    }
}
