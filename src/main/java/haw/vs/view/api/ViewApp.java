
package haw.vs.view.api;


import haw.vs.common.ICallee;
import haw.vs.common.properties.AppType;
import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.view.javafx.JavaFXApp;
import javafx.application.Application;

import java.util.concurrent.CountDownLatch;

/**
 * Der Startpunkt für die Player um die App zu starten
 */
public class ViewApp implements IComponentApp, Runnable {

    public static CountDownLatch viewStartedCountDownLatch;

    public static CountDownLatch everyBodyElseStartedCountDownLatch;

    public ViewApp() {
    }

    @Override
    public void run() {
        Application.launch(JavaFXApp.class);
    }

    @Override
    public void startApp(CountDownLatch viewStartedCountDownLatch, CountDownLatch everyBodyElseStartedCountDownLatch) throws MethodNameAlreadyExistsException {
        ViewApp.viewStartedCountDownLatch = viewStartedCountDownLatch;
        ViewApp.everyBodyElseStartedCountDownLatch = everyBodyElseStartedCountDownLatch;

        Thread viewThread = new Thread(this, "ViewAppThread");
        viewThread.start();

        try {
            viewStartedCountDownLatch.await();
            if (PropertiesHelper.getAppType() == AppType.DISTRIBUTED) {
                ICallee viewFacade = (ICallee) ViewFactory.getView(ComponentType.VIEW);
                viewFacade.register();
            }
        } catch (PropertiesException | NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
