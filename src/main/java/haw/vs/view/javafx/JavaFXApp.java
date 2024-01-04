package haw.vs.view.javafx;

import edu.cads.bai5.vsp.tron.view.ITronView;
import haw.vs.view.api.IPlayerInputHandler;
import haw.vs.view.api.ViewFactory;
import haw.vs.view.overlay.Looser;
import haw.vs.view.overlay.MainMenuNew;
import haw.vs.view.overlay.PlayerCountViewNew;
import haw.vs.view.overlay.Winner;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    public ITronView tronView;
    IPlayerInputHandler inputHandler; //TODO eigentlich hat die JavaFXApp das nicht sondern die TronView!


    @Override
    public void start(Stage stage) throws Exception {
        tronView = TronView.getInstance();
        inputHandler = ViewFactory.getInputHandler();

        // Build and register main menu to put player count in the form
        MainMenuNew mainMenu = new MainMenuNew("menu.css", tronView);
        tronView.registerOverlay("main", mainMenu);

        // Build and register main menu to put player count in the form
        PlayerCountViewNew playerCountView = new PlayerCountViewNew("menu.css", tronView);
        tronView.registerOverlay("playerCount", playerCountView);

        // Build and register winner menu to put player count in the form
        Winner winnerMenu = new Winner("menu.css", tronView);
        tronView.registerOverlay("winner", winnerMenu);

        // Build and register looser menu to put player count in the form
        Looser looserMenu = new Looser("menu.css", tronView);
        tronView.registerOverlay("looser", looserMenu);

        //init view
        tronView.init();
        //test tronView.showOverlay("playerCount");


        // configure and show stage
        stage.setTitle("TRON - the best game ever");
        stage.setScene(tronView.getScene());


        //set event handlers for user input
        tronView.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                inputHandler.onKeyPressed(event.getCode().toString());

            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        JavaFXApp.launch(args);
    }
}

