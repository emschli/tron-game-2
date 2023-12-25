package haw.vs.view.common;

/**
 * Datenstruktur um die Playerinfo zu haben
 */
public class PlayerInfo {

    private static long playerId;

    private static long matchId;

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
}

