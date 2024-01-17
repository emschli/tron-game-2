package haw.vs.view.api;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.awt.*;

/**
 * Datenstruktur um die Playerinfo zu haben
 */
public class PlayerInfo {

    private static long playerId = 1;

    private static long matchId;

    private static int noOfPlayers;
    private static int actualNoOfPlayers;

    public static String color;
    public static StringProperty colorProperty;

    public static StringProperty colorTextProperty;
    public static StringProperty waitingScreenText;


    static {
        waitingScreenText = new SimpleStringProperty("Waiting for more players to join the game.\nThere are already " + PlayerInfo.getActualNoOfPlayers() + " of " +
                PlayerInfo.getNoOfPlayers() + " Players ready. \n");
        colorTextProperty = new SimpleStringProperty("Your color is: RED");
        colorProperty = new SimpleStringProperty("CYAN");

    }

    public static long getPlayerId() {
        return playerId;
    }

    public static void setPlayerId(long playerId) {
        PlayerInfo.playerId = playerId;
    }

    public static long getMatchId() {
        return matchId;
    }

    public static void setMatchId(long matchId) {
        PlayerInfo.matchId = matchId;
    }

    public static int getNoOfPlayers() {
        return noOfPlayers;
    }

    public static void setNoOfPlayers(int noOfPlayers) {
        PlayerInfo.noOfPlayers = noOfPlayers;
        updateStringProperty();
    }

    public static int getActualNoOfPlayers() {
        return actualNoOfPlayers;
    }

    public static void setActualNoOfPlayers(int actualNoOfPlayers) {
        PlayerInfo.actualNoOfPlayers = actualNoOfPlayers;
        updateStringProperty();
    }

    public static String getColor() {
        return color;
    }

    public static void setColor(String color) {
        PlayerInfo.color = color;
        updateColorTextProperty();
        updateColorProperty();
    }

    private static void updateStringProperty() {
        Platform.runLater(() -> {
            waitingScreenText.set("Waiting for more players to join the game.\nThere are already " + PlayerInfo.getActualNoOfPlayers() + " of " +
                    PlayerInfo.getNoOfPlayers() + " Players ready. \n");
        });
    }
    private static void updateColorTextProperty() {
        Platform.runLater(() -> {
            colorTextProperty.set("Your color is: " + PlayerInfo.getColor());
        });
    }   private static void updateColorProperty() {
        Platform.runLater(() -> {
            colorProperty.set(PlayerInfo.getColor());
        });
    }
}

