package haw.vs.view.overlay;

import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.ViewUtility;
import haw.vs.view.api.PlayerInputHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static haw.vs.view.javafx.TronView.tronView;

/**
 * The View fot the PlayerCountView. This is shown if the player waits for other Players to join the game, and the
 * PlayerCounter is increased with every player who joins the (future) match.
 * There is a button which can be clicked to quit the (future) match and go beck to main menu.
 */
public class PlayerCountView extends VBox {
    //Label to tell the player what is happening.
    private final Label labelCount;
    //button to quit the (future) match and go back to main menu
    private final Button btnCancel;

//TODO this button should be deleted in the future, just for testing to increase the counter
    private final Button btnCount;
//counter which counts the number of Players
    private int counter;

    public PlayerCountView(String stylesheet, ITronView view, PlayerInputHandler inputHandler) {
        super(20.0);
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        labelCount = new Label("Waiting for more players to join the game.\nThere are already " + counter + " Players waiting.");
        labelCount.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        //TODO this button needs to be deleted in the future
        btnCount = new Button("Increase Count");
        btnCount.setOnAction(event -> {
            counter++;
            labelCount.setText("Waiting for more players to join the game.\nThere are " + counter + " Players waiting.");
            if(counter >=4){
                tronView.hideOverlays();
                StartMenu startMenu = new StartMenu("menu.css", tronView);
                tronView.registerOverlay("start", startMenu);
                tronView.init();
                tronView.showOverlay("start");
            }
        });
        //Add the button to cancel/go back to main menu
        //TODO add right handler
        btnCancel = new Button("Cancel");
        btnCancel.setOnAction(event -> {
            //TODO inputController.onCancel() cancelWait();
            //back to main
            tronView.hideOverlays();
            MainMenu mainMenu = new MainMenu("menu.css", tronView, inputHandler);
            tronView.registerOverlay("main", mainMenu);
            tronView.init();
            tronView.showOverlay("main");
        });

        //add all the elements to the overlay
        this.getChildren().add(labelCount);
        this.getChildren().add(btnCancel);
        this.getChildren().add(btnCount);

    }
}