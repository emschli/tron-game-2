package haw.vs.view.overlay;

import edu.cads.bai5.vsp.tron.view.ViewUtility;
import haw.vs.view.javafx.ITronView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Just a Menu to have a button which has to be klicked before the game starts
 * May be deleted later (or we write a counter 3-2-1-Go!?)
 */
public class StartMenu extends VBox {
    private final Label labelReady;
    private final Button btnStart;

    public StartMenu(String stylesheet, ITronView view) {
        super(20.0);
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        labelReady = new Label("Ready?");
        labelReady.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        btnStart = new Button("Start Game");
        btnStart.setOnAction(event -> {
            view.hideOverlays();
        });

        this.getChildren().add(labelReady);
        this.getChildren().add(btnStart);
    }
}