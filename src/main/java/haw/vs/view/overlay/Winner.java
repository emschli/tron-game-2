package haw.vs.view.overlay;

import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.ViewUtility;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static haw.vs.view.javafx.TronView.tronView;

/**
 * Just a Menu to have a button which has to be klicked before the game starts
 * May be deleted later (or we write a counter 3-2-1-Go!?)
 */
public class Winner extends VBox {
    private final Label labelWinner;
    private final Button backToMain;

    public Winner(String stylesheet, ITronView view) {
        super(20.0);
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        labelWinner = new Label("You won!");
        labelWinner.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        backToMain = new Button("Back To Main Menu");
        backToMain.setOnAction(event -> {
            tronView.hideOverlays();
            MainMenuNew mainMenu = new MainMenuNew("menu.css", tronView);
            tronView.registerOverlay("main", mainMenu);
            tronView.init();
            tronView.showOverlay("main");
        });

        this.getChildren().add(labelWinner);
        this.getChildren().add(backToMain);
    }
}