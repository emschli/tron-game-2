package haw.vs.view.api;

/**
 * Datenstruktur um die Playerinfo zu haben
 */
public class PlayerInfo {

    private static long playerId;

    private static long matchId;

    private static final long ONE = 1;

    /**
     * Default PlayerId is 1
     */
    public PlayerInfo() {
        this.playerId = ONE;
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
}
