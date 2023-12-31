package haw.vs.view.overlay;

import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.ViewUtility;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static haw.vs.view.javafx.TronView.tronView;

public class PlayerCountView extends VBox {
    private final Label labelCount;

    //private final Button btnCancel;

    private final Button btnCount;

    private int count;

    public PlayerCountView(String stylesheet, ITronView view) {
        super(20.0);
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        labelCount = new Label("Waiting for more players to join the game.\nThere are  " + count + " Players waiting.");
        labelCount.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        btnCount = new Button("Increase Count");
        btnCount.setOnAction(event -> {
            count++;
            labelCount.setText("Waiting for more players to join the game.\nThere are " + count + " Players waiting.");
            if(count>=4){
                tronView.hideOverlays();
                StartMenu startMenu = new StartMenu("menu.css", view);
                tronView.registerOverlay("start", startMenu);
                tronView.init();
                tronView.showOverlay("start");

            }
        });

      //  btnCancel = new Button("Cancel");
      //  btnCount.setOnAction(event -> {
            //TODO inputController.onCancel() cancelWait();
            //back to main

       // });


        this.getChildren().add(labelCount);
     //   this.getChildren().add(btnCancel);
        this.getChildren().add(btnCount);

    }
}