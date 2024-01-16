package haw.vs.view.overlay;

import haw.vs.common.ViewUtility;
import haw.vs.view.api.PlayerInfo;
import haw.vs.view.javafx.ITronView;
import haw.vs.view.translateAI.TranslationServiceAI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static haw.vs.view.javafx.TronView.inputHandler;

/**
 * This Overlay is the Main Menu. The Player is asked about the PlayerCount (it should be within 1-4.
 * - If there is no Input given, there will be an altert/warning and the Player is asked to give an input again.
 * - If the input is correct, the overlay hides and the PlayerCountView-Overlay is shown.
 * -> the input is saved in the PlayerInfo.noOfPlayers
 * -> the InputHandler.onGameStart(noOfPlayers) is called
 */
public class MainMenu extends VBox {
    private static final String BLOCKING_WARNING_ALERT = "This is a warning";

    private final Label labelMain;
    private final Label feedbackLbl;
    private final Button btnJoin;

    private final TextField textField;
    private TranslationServiceAI translationServiceAI;

    public MainMenu(String stylesheet, ITronView view) {
        super(20.0);
        translationServiceAI = new TranslationServiceAI();
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        labelMain = new Label(translationServiceAI.translateText("How many Players?"));
        labelMain.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");
        textField = new TextField();

        //add textfield and listener
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old, String neu) {
                //if player types a wrong value, there will be the default value 1
                if(!neu.matches("[1-4]?")){
                textField.setText("1");
                }
            }
        });
        //add feedbacklabel for warning alert
        feedbackLbl = new Label(translationServiceAI.translateText("You have to select a number between 1-4"));
        //add button to enter the in text field setted number
        btnJoin = new Button(translationServiceAI.translateText("Join Game"));
        btnJoin.setOnAction(event -> {
            if(textField.getText().equals("")){
                //if there is no value there will be an alert and player can enter a value again
                var alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(translationServiceAI.translateText(BLOCKING_WARNING_ALERT));
                alert.setHeaderText(translationServiceAI.translateText(BLOCKING_WARNING_ALERT));
                alert.setContentText(feedbackLbl.getText());
                alert.showAndWait().ifPresent((btnType) -> {
                    feedbackLbl.setText(translationServiceAI.translateText("Thats all from ") + translationServiceAI.translateText(BLOCKING_WARNING_ALERT));
                });
            } else {
                //if the value is allowed, the overlay hides
                view.hideOverlays();
                //the input was allowed -> and after the overlay hided the PlayerCountView needs to be shown:
                //get the IntegerValue of the text field
                PlayerInfo.setNoOfPlayers(Integer.valueOf(textField.getText()));
                //use PlayerInputHandler
                inputHandler.onGameStart(PlayerInfo.getNoOfPlayers());
            }

        });

        //add all the elements in the MainMenu View
        this.getChildren().add(labelMain);
        this.getChildren().add(textField);
        this.getChildren().add(btnJoin);
    }
}
