package haw.vs.view.javafx;

import haw.vs.view.api.ViewApp;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

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

        //test tronView.showOverlay("playerCount");

        // configure and show stage
        stage.setTitle("TRON - the best game ever");
        stage.setScene(tronView.getScene());
        tronView.showOverlay("main");

        viewStartedCountDownLatch.countDown();
        everyBodyElseStartedCountDownLatch.await();
        stage.show();
    }

}

