package haw.vs;

import haw.vs.common.loggingService.LoggingService;
import haw.vs.common.properties.AppType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.model.matchmanager.MatchManagerApp;
import haw.vs.view.api.IViewApp;
import haw.vs.view.api.ViewApp;

public class Main {
    public static void main(String[] args) {
        LoggingService.setup();
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
                default:
                    // Handle other cases here if needed
                    break;
            }
//            switch (appType) {
//                case STANDALONE -> {
//                    IViewApp viewApp = new ViewApp();
//                    MatchManagerApp matchManagerApp = new MatchManagerApp();
//
//                    matchManagerApp.startApp();
//                    viewApp.startApp();
//                }
//            }
        } catch (PropertiesException e) {
            throw new RuntimeException(e);
        }

    }
}
