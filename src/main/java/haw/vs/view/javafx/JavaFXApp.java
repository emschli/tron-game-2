package haw.vs.view.javafx;

import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    public ITronView tronView;

    @Override
    public void start(Stage stage) throws Exception {
        tronView = TronView.getInstance();

        // configure and show stage
        stage.setTitle("TRON");
        stage.setScene(tronView.getScene());
        tronView.showOverlay("main");

        stage.show();
    }

}

