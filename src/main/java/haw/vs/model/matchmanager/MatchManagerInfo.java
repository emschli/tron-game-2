package haw.vs.model.matchmanager;

public class MatchManagerInfo {
    private static Long MATCH_MANAGER_ID;

    public static void setMatchManagerId(Long id) {
        MATCH_MANAGER_ID = id;
    }

    public static Long getMatchManagerId() {
        return MATCH_MANAGER_ID;
    }
}
