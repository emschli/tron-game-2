package haw.vs.model.matchmanager.impl.state;

public class Colors {
    public static String getColor(int playerNumber) {
        if (playerNumber > Matches.MAX_NUMBER_OF_PLAYERS) {
            throw new IllegalArgumentException(String.format("Player number must be < %s, but is %s", Matches.MAX_NUMBER_OF_PLAYERS, playerNumber));
        }
        switch (playerNumber) {
            case 1:
                return "blue";
            case 2:
                return "red";
            case 3:
                return "green";
            case 4:
                return "yellow";
            default:
                return null;
        }
    }
}
