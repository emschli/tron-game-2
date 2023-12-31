package haw.vs.view.overlay;

import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.ViewUtility;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainMenu extends VBox {
    private static final String BLOCKING_WARNING_ALERT = "This is a warning";

    private final Label labelMain;
    private final Label feedbackLbl;
    private final Button btnJoin;

    private final TextField textField;

    public MainMenu(String stylesheet, ITronView view) {
        super(20.0);
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        labelMain = new Label("How many Players?");
        labelMain.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        textField = new TextField();
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old, String neu) {
                if(!neu.matches("[1-4]?")){
                textField.setText("1");
                }
            }
        });

        feedbackLbl = new Label("You have to select a number between 1-4");
        btnJoin = new Button("Join Game");
        btnJoin.setOnAction(event -> {
            if(textField.getText().equals("")){
                var alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(BLOCKING_WARNING_ALERT);
                alert.setHeaderText(BLOCKING_WARNING_ALERT);
                alert.setContentText(feedbackLbl.getText());
                alert.showAndWait().ifPresent((btnType) -> {
                    feedbackLbl.setText("Thats all from " + BLOCKING_WARNING_ALERT);
                });
            } else {
                view.hideOverlays();
            }

            PlayerCountView playerCountView = new PlayerCountView("playerCount", view);
            view.registerOverlay("playerCount", playerCountView);//Waiting for other PlayersView
            view.init();
            view.showOverlay("playerCount");
        });
//TODO view oder tronView??
        this.getChildren().add(labelMain);
        this.getChildren().add(textField);
        this.getChildren().add(btnJoin);
    }
}
