package haw.vs.view.javafx;

import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.TronView;
import haw.vs.view.overlay.GameModelTest;
import haw.vs.view.overlay.MainMenu;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    public ITronView tronView;
    public final static String VIEW_CONFIG_FILE = "view_custom.properties";
    private final double TARGET_FPS = 60.0;
    private final double TARGET_FRAME_TIME = 1.0 / TARGET_FPS;


    @Override
    public void start(Stage stage) throws Exception {
        tronView = new TronView(VIEW_CONFIG_FILE);

        // Build and register start menu
        MainMenu playerCountView = new MainMenu("menu.css", tronView);
        tronView.registerOverlay("count", playerCountView);

        // init view and show start menu
        tronView.init();
        tronView.showOverlay("count");

        // configure and show stage
        stage.setTitle("TRON - Light Cycles");
        stage.setScene(tronView.getScene());

        GameModelTest gameModelTest = new GameModelTest(40, 40);

        //set event handlers for user input
        tronView.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameModelTest.onKeyPressed(event.getCode().toString());
            }
        });

        tronView.getScene().setOnKeyReleased(event -> gameModelTest.onKeyReleased(event.getCode().toString()));

        //Eigentlicher Game-Loop
        new AnimationTimer()
        {
            private long lastUpdated = System.nanoTime();
            @Override
            public void handle(long now) {
                double elapsedSeconds = (now - lastUpdated) / 1e9;

                if (elapsedSeconds >= TARGET_FRAME_TIME) {
                    gameModelTest.update();
                    tronView.clear();
                    tronView.draw(gameModelTest.getCurrentPosition(), Color.BEIGE);
                    lastUpdated = now;
                }
            }
        }.start();

        stage.show();
    }

    public static void main(String[] args) {
        JavaFXApp.launch(args);
    }
}
