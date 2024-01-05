
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
        Thread viewThread = new Thread(this);
        viewThread.start();
    }
}
