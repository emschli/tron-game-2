package haw.vs.view.overlay;

import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.ViewUtility;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static haw.vs.view.javafx.TronView.inputHandler;

/**
 * The View fot the PlayerCountView. This is shown if the player waits for other Players to join the game.
 * There is a button which can be clicked to quit the (future) match and go back to main menu.
 */
public class PlayerCountViewNew extends VBox {
    //Label to tell the player what is happening.
    private final Label labelCount;
    //button to quit the (future) match and go back to main menu
    private final Button btnCancel;

    //counter which counts the number of Players
    private int counter;

    public PlayerCountViewNew(String stylesheet, ITronView view, int actualNumOfPlayers, int targetNumOfPlayers) {
        super(20.0);
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);
        this.counter = actualNumOfPlayers;

        labelCount = new Label("Waiting for more players to join the game.\nThere are already" + counter + " of " +
                targetNumOfPlayers + " Players. \n");
        labelCount.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        //Add the button to cancel/go back to main menu
        btnCancel = new Button("Cancel");
        btnCancel.setOnAction(event -> {
            //call method in PlayerInputHandler to cancel the process of waiting and leave the (future) match
            inputHandler.onCancel();

        });

        //add all the elements to the overlay
        this.getChildren().add(labelCount);
        this.getChildren().add(btnCancel);

    }
}