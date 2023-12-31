package haw.vs.model.matchmanager.state;

public class Colors {
    public static String getColor(int playerNumber) {
        if (playerNumber > Matches.MAX_NUMBER_OF_PLAYERS) {
            throw new IllegalArgumentException(String.format("Player number must be < %s, but is %s", Matches.MAX_NUMBER_OF_PLAYERS, playerNumber));
        }
        return switch (playerNumber) {
            case 1 -> "blue";
            case 2 -> "red";
            case 3 -> "green";
            case 4 -> "yellow";
            default -> null;
        };
    }
}
