package haw.vs.view.overlay;

import haw.vs.common.ViewUtility;
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
public class Looser extends VBox {
    private final Label labelLooser;
    private final Button backToMain;

    public Looser(String stylesheet, ITronView view) {
        super(20.0);
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        labelLooser = new Label("You lost!");
        labelLooser.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        backToMain = new Button("Back To Main Menu");
        backToMain.setOnAction(event -> {
            //TODO back to main anzeigen, funktioniert das?
            view.clear();
            view.hideOverlays();
            view.showOverlay("main");        });

        this.getChildren().add(labelLooser);
        this.getChildren().add(backToMain);
    }
}