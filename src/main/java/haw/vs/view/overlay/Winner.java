package haw.vs.view.overlay;

import edu.cads.bai5.vsp.tron.view.ViewUtility;
import haw.vs.view.javafx.ITronView;
import haw.vs.view.translateAI.TranslationServiceAI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Just a Menu to have a button which has to be klicked before the game starts
 * May be deleted later (or we write a counter 3-2-1-Go!?)
 */
public class Winner extends VBox {
    private final Label labelWinner;
    private final Button backToMain;

    private TranslationServiceAI translationServiceAI;

    public Winner(String stylesheet, ITronView view) {
        super(20.0);
        translationServiceAI = new TranslationServiceAI();
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        labelWinner = new Label(translationServiceAI.translateText("You won!"));
        labelWinner.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        backToMain = new Button(translationServiceAI.translateText("Back To Main Menu"));
        backToMain.setOnAction(event -> {
            view.clear();
            view.hideOverlays();
            view.showOverlay("main");
        });

        this.getChildren().add(labelWinner);
        this.getChildren().add(backToMain);
    }
}