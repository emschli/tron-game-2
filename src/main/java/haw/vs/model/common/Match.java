package haw.vs.model.common;

import haw.vs.middleware.common.DoNotLookHere;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Match {
    private long matchId;
    private MatchState state;
    private int numberOfPlayers;
    private int maxGridX;
    private int maxGridY;
    private List<Player> players = new ArrayList<>();

    private Long matchManagerId;
    private Long tickStamp = 0L;

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public Player getPlayerById(long playerId) {
        return players.stream().filter(player -> player.getPlayerId() == playerId).findFirst().orElse(null);
    }

    public void removePlayer(long playerId) {
        players.removeIf(player -> player.getPlayerId() == playerId);
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

    public int getMaxGridY() {
        return maxGridY;
    }

    public void setMaxGridY(int maxGridY) {
        this.maxGridY = maxGridY;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Long getMatchManagerId() {
        return matchManagerId;
    }

    public void setMatchManagerId(Long matchManagerId) {
        this.matchManagerId = matchManagerId;
    }

    public Long getTickStamp() {
        return tickStamp;
    }

    public void setTickStamp(Long tickStamp) {
        this.tickStamp = tickStamp;
    }

    @DoNotLookHere
    public boolean isFull() {
        return numberOfPlayers == players.size();
    }

    public List<Player> getAlivePlayers() {
        return players.stream()
                .filter(Player::isAlive)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return matchId == match.matchId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId);
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", state=" + state +
                ", numberOfPlayers=" + numberOfPlayers +
                ", maxGridX=" + maxGridX +
                ", maxGridY=" + maxGridY +
                ", players=" + players +
                '}';
    }

    public Match copy() {
        Match match = new Match();
        match.setMatchId(this.matchId);
        match.setState(this.state);
        match.setNumberOfPlayers(this.numberOfPlayers);
        match.setMaxGridX(this.maxGridX);
        match.setMaxGridY(this.maxGridY);
        List<Player> players = new ArrayList<>();
        for (Player player : this.players) {
            players.add(player.copy());
        }
        match.setPlayers(players);
        match.setMatchManagerId(this.matchManagerId);
        match.setTickStamp(this.tickStamp);
        return match;
    }
}
