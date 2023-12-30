package haw.vs.view.test;

import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.ViewUtility;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PlayerCountViewTest extends VBox {
    private final Label labelCount;

    //private final Button btnCancel;

    private final Button btnCount;

    private int count;

    public PlayerCountViewTest(String stylesheet, ITronView view) {
        super(20.0);
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        labelCount = new Label("Waiting for more players to join the game.\nThere are  " + count + " Players waiting.");
        labelCount.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        btnCount = new Button("Increase Count");
        btnCount.setOnAction(event -> {
            count++;
            System.out.println("Hey, count");
            labelCount.setText("Waiting for more players to join the game.\nThere are " + count + " Players waiting.");
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