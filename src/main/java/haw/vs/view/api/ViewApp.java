
package haw.vs.view.api;


import haw.vs.view.javafx.JavaFXApp;

/**
 * Der Startpunkt für die Player um die App zu starten
 */
public class ViewApp implements IViewApp {


    private ViewApp() {
    }

    public static void main(String[] args) {
        JavaFXApp.main(args);

    }

    @Override
    public void startApp() {
        System.out.println("test");
//TODO
    }
}
