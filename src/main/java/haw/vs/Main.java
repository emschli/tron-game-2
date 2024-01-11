package haw.vs;

import haw.vs.common.properties.AppType;
import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.ControllerApp;
import haw.vs.middleware.MiddlewareApp;
import haw.vs.model.gamelogic.GameLogicApp;
import haw.vs.model.matchmanager.MatchManagerApp;
import haw.vs.view.api.IViewApp;
import haw.vs.view.api.ViewApp;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            AppType appType = PropertiesHelper.getAppType();
            switch (appType) {
                case STANDALONE:
                    IViewApp viewApp = new ViewApp();
                    MatchManagerApp matchManagerApp = new MatchManagerApp();

                    viewApp.startApp();
                    matchManagerApp.startApp();
                    break;
                // Add other cases if needed
                case DISTRIBUTED:
                    new MiddlewareApp().startApp();

                    List<ComponentType> componentsToBeStarted = PropertiesHelper.getAllComponents();
                    for (ComponentType componentType : componentsToBeStarted) {
                        switch (componentType) {
                            case VIEW:
                                new ViewApp().startApp();
                                break;
                            case CONTROLLER:
                                new ControllerApp().startApp();
                                break;
                            case MATCH_MANAGER:
                                new MatchManagerApp().startApp();
                                break;
                            case GAME_LOGIC:
                                new GameLogicApp().startApp();
                        }
                    }
                default:
                    throw new RuntimeException("Invalid AppType");
            }
        } catch (PropertiesException e) {
            throw new RuntimeException(e);
        }

    }
}
