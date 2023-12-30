
package haw.vs.view.api;


import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.TronView;
import haw.vs.view.test.GameModelTest;
import haw.vs.view.test.PlayerCountViewTest;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Der Startpunkt für die Player um die App zu starten
 */
public class ViewApp extends Application implements IViewApp{
    public final static String VIEW_CONFIG_FILE = "view_custom.properties";
    private final double TARGET_FPS = 60.0;
    private final double TARGET_FRAME_TIME = 1.0 / TARGET_FPS;


    @Override
    public void start(Stage stage) throws Exception {
        ITronView view = new TronView(VIEW_CONFIG_FILE);

        // Build and register start menu
        PlayerCountViewTest startMenuTest = new PlayerCountViewTest("menu.css", view);
        view.registerOverlay("start", startMenuTest);

        // init view and show start menu
        view.init();
        view.showOverlay("start");

        // configure and show stage
        stage.setTitle("TRON - Light Cycles");
        stage.setScene(view.getScene());

        GameModelTest gameModelTest = new GameModelTest(40, 40);

        //set event handlers for user input
        view.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameModelTest.onKeyPressed(event.getCode().toString());
            }
        });

        view.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameModelTest.onKeyReleased(event.getCode().toString());
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
                    view.clear();
                    view.draw(gameModelTest.getCurrentPosition(), Color.BEIGE);
                    lastUpdated = now;
                }
            }
        }.start();

        stage.show();
    }

    @Override
    public void startApp() {
        System.out.println("test");
//TODO
    }
    public static void main(String[] args) {
        launch(args);
    }

}