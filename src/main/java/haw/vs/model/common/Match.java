package haw.vs.model.common;

import java.util.List;
import java.util.Objects;

public class Match {
    private long matchId;
    private MatchState state;
    private int numberOfPlayers;
    private int maxGridX;
    private int getMaxGridY;
    private List<Player> players;

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public MatchState getState() {
        return state;
    }

    public void setState(MatchState state) {
        this.state = state;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getMaxGridX() {
        return maxGridX;
    }

    public void setMaxGridX(int maxGridX) {
        this.maxGridX = maxGridX;
    }

    public int getGetMaxGridY() {
        return getMaxGridY;
    }

    public void setGetMaxGridY(int getMaxGridY) {
        this.getMaxGridY = getMaxGridY;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return matchId == match.matchId;
    }
}
