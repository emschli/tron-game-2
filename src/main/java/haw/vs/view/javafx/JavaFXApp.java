package haw.vs.view.javafx;

import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    public ITronView tronView;

    @Override
    public void start(Stage stage) throws Exception {
        tronView = TronView.getInstance();

        //test tronView.showOverlay("playerCount");

        // configure and show stage
        stage.setTitle("TRON - the best game ever");
        stage.setScene(tronView.getScene());

        stage.show();
    }

    public static void main(String[] args) {
        JavaFXApp.launch(args);
    }
}

