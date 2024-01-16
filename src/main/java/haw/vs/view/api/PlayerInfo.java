package haw.vs.view.api;

import haw.vs.view.translateAI.TranslationServiceAI;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Datenstruktur um die Playerinfo zu haben
 */
public class PlayerInfo {

    private static long playerId = 1;

    private static long matchId;

    private static int noOfPlayers;
    private static int actualNoOfPlayers;

    public static StringProperty waitingScreenText;

    private static TranslationServiceAI translationServiceAI = new TranslationServiceAI();;

    static {
        waitingScreenText = new SimpleStringProperty(translationServiceAI.translateText("Waiting for more players to join the game.\nThere are already ") + " "+
                PlayerInfo.getActualNoOfPlayers() + " " + translationServiceAI.translateText(" of ") + " "+
                PlayerInfo.getNoOfPlayers() + " " + translationServiceAI.translateText(" Players ready. \n"));
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

    private static void updateStringProperty() {
        Platform.runLater(() -> {
            waitingScreenText.set(translationServiceAI.translateText("Waiting for more players to join the game.\nThere are already ") + " "+
                    PlayerInfo.getActualNoOfPlayers()+ " " + translationServiceAI.translateText(" of ") + " " +
                    PlayerInfo.getNoOfPlayers() + " "+ translationServiceAI.translateText(" Players ready. \n"));
        });
    }
}

