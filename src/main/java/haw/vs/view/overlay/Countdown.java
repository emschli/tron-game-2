package haw.vs.view.overlay;

import haw.vs.common.ViewUtility;
import haw.vs.view.api.PlayerInfo;
import haw.vs.view.javafx.ITronView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javafx.event.EventHandler;

/**
 * The View fot the PlayerCountView. This is shown if the player waits for other Players to join the game.
 * There is a button which can be clicked to quit the (future) match and go back to main menu.
 */
public class Countdown extends VBox {
    //Label to tell the player what is happening.
    private Label labelCount;
    private Text yourColor;
    //starttime
    private static final Integer STARTTIME = 10;
    //timeline to track the actual counted time
    private Timeline timeline;
    //
    private Integer timeSeconds = STARTTIME;

    private Color color;


    public Countdown(String stylesheet, ITronView view) {
        super(20.0);
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        //get color
       // String getColor = PlayerInfo.getColor();
        String getColor = "red";
        color = Color.web(getColor);

        //label configuration
        labelCount = new Label();
        labelCount.setText(timeSeconds.toString());
        labelCount.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        //text configuration
        yourColor = new Text();
        yourColor.setText("Your color is " + getColor);
        yourColor.setFill(color);
        yourColor.setStyle("-fx-font: bold 20px \"Sans\";\n");

        //labelCount.textProperty().bind(PlayerInfo.waitingScreenText);

        timeSeconds = STARTTIME;

        //update
        labelCount.setText(timeSeconds.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() {
            @Override
            public void handle(Event event) {
                timeSeconds--;
                labelCount.setText(timeSeconds.toString());
                if (timeSeconds <= 0) {
                    timeline.stop();
                    view.hideOverlays();
                }
            }
        }));
        timeline.playFromStart();


        //add all the elements to the overlay
        this.getChildren().add(labelCount);
        this.getChildren().add(yourColor);

    }
}