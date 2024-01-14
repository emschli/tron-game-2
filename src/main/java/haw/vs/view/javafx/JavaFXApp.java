package haw.vs.view.javafx;

import haw.vs.view.api.ViewApp;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.concurrent.CountDownLatch;

/**
 * Entrypoint for JavaFX App.
 */
public class JavaFXApp extends Application {
    public ITronView tronView;

    private CountDownLatch viewStartedCountDownLatch;

    private CountDownLatch everyBodyElseStartedCountDownLatch;

    @Override
    public void init() throws Exception {
        viewStartedCountDownLatch = ViewApp.viewStartedCountDownLatch;
        everyBodyElseStartedCountDownLatch = ViewApp.everyBodyElseStartedCountDownLatch;
    }

    @Override
    public void start(Stage stage) throws Exception {
        tronView = TronView.getInstance();

        // configure and show stage
        stage.setTitle("TRON - the best game ever");
        stage.setScene(tronView.getScene());

        //set event handler for closing window and exit game
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    System.exit(0);
                } catch (Exception e) {
                    throw new RuntimeException("Could not terminate Application.");
                }
            }
        });

        tronView.showOverlay("main");

        viewStartedCountDownLatch.countDown();
        everyBodyElseStartedCountDownLatch.await();
        stage.show();
    }

}

