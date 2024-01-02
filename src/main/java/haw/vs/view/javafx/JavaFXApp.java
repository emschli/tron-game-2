package haw.vs.view.javafx;

import edu.cads.bai5.vsp.tron.view.ITronView;
import haw.vs.view.api.IPlayerInputHandler;
import haw.vs.view.api.ViewFactory;
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
    IPlayerInputHandler inputHandler; //TODO eigentlich hat die JavaFXApp das nicht sondern die TronView!
    private final double TARGET_FPS = 60.0;
    private final double TARGET_FRAME_TIME = 1.0 / TARGET_FPS;


    @Override
    public void start(Stage stage) throws Exception {
        tronView = TronView.getInstance();
        inputHandler = ViewFactory.getInputHandler();


        // Build and register start menu
        MainMenu mainMenu = new MainMenu("menu.css", tronView, inputHandler);
        tronView.registerOverlay("main", mainMenu);

        // init view and show start menu
        tronView.init();
        tronView.showOverlay("main");


        // configure and show stage
        stage.setTitle("TRON - the best game ever");
        stage.setScene(tronView.getScene());


        GameModelTest gameModelTest = new GameModelTest(40, 40);
        //set event handlers for user input
        tronView.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                inputHandler.onKeyPressed(event.getCode().toString());
            }
        });


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

