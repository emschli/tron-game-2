
package haw.vs.view.api;


import haw.vs.view.javafx.JavaFXApp;
import javafx.application.Application;

/**
 * Der Startpunkt für die Player um die App zu starten
 */
public class ViewApp implements IViewApp {


    private ViewApp() {
    }


    @Override
    public void startApp() {
        Application.launch(JavaFXApp.class);
    }
}
