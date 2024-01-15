package haw.vs.model.matchmanager.tick;

public class TickSummary {
    private static int matchesSentToGameLogic = 0;
    private static int matchesReceivedFromGameLogic = 0;
    private static int matchesSentToView = 0;

    public static void addMatchesSentToGameLogic() {
        matchesSentToGameLogic += 1;
    }

    public static void addMatchesReceivedFromGameLogic() {
        matchesReceivedFromGameLogic += 1;
    }

    public static void addMatchesSentToView() {
        matchesSentToView += 1;
    }

    public static void clearAll() {
        matchesSentToGameLogic = 0;
        matchesReceivedFromGameLogic = 0;
        matchesSentToView = 0;
    }

    public static boolean anythingHappened() {
        return matchesSentToGameLogic != 0 || matchesReceivedFromGameLogic != 0 || matchesSentToView != 0;
    }

    public static String getTickSummaryMessage() {
        return String.format("---------TICK-SUMMARY--------------\nMatches sent to GameLogic:\t\t\t%s\nMatches received from GameLogic:\t%s\nMatches sent to View:\t\t\t\t%s\n-----------------------------------", matchesSentToGameLogic, matchesReceivedFromGameLogic, matchesSentToView);
    }
}
