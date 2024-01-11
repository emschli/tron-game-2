
package haw.vs.view.api;


import haw.vs.view.javafx.JavaFXApp;
import javafx.application.Application;

/**
 * Der Startpunkt für die Player um die App zu starten
 */
public class ViewApp implements IViewApp, Runnable {


    public ViewApp() {
    }

    @Override
    public void run() {
        Application.launch(JavaFXApp.class);
    }

    @Override
    public void startApp() {
        try {
            if (PropertiesHelper.getAppType() == AppType.DISTRIBUTED) {
                ICallee viewFacade = (ICallee) ViewFactory.getView();
                viewFacade.register();
            }
        } catch (PropertiesException | NameServiceException e) {
            throw new RuntimeException(e);
        }

        Thread viewThread = new Thread(this);
        viewThread.start();
    }
}
