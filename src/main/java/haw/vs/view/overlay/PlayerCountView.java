package haw.vs.view.overlay;

import haw.vs.common.ViewUtility;
import haw.vs.view.api.PlayerInfo;
import haw.vs.view.javafx.ITronView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static haw.vs.view.javafx.TronView.inputHandler;

/**
 * The View fot the PlayerCountView. This is shown if the player waits for other Players to join the game.
 * There is a button which can be clicked to quit the (future) match and go back to main menu.
 */
public class PlayerCountView extends VBox {
    //Label to tell the player what is happening.
    private final Label labelCount;
    //button to quit the (future) match and go back to main menu
    private final Button btnCancel;

    //textfied for the color
    private Text yourColor;
    //color of the player

    public PlayerCountView(String stylesheet, ITronView view) {
        super(20.0);
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        //Label for the counting
        labelCount = new Label();
        labelCount.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");
        labelCount.textProperty().bind(PlayerInfo.waitingScreenText);

        //text configuration for the color text
        yourColor = new Text();
        yourColor.textProperty().bind(PlayerInfo.colorTextProperty);
        yourColor.setStyle("-fx-font: bold 20px \"Sans\";\n");

        // add listener to show the changed color in the overlay
        PlayerInfo.colorProperty.addListener((observable, oldValue, newValue) -> {
            yourColor.setFill(Color.web(newValue.toString()));
        });

        //Add the button to cancel/go back to main menu
        btnCancel = new Button("Cancel");
        btnCancel.setOnAction(event -> {
            //call method in PlayerInputHandler to cancel the process of waiting and leave the (future) match
            view.hideOverlays();
            inputHandler.onCancel();
        });

        //add all the elements to the overlay
        this.getChildren().add(labelCount);
        this.getChildren().add(yourColor);
        this.getChildren().add(btnCancel);

    }
}