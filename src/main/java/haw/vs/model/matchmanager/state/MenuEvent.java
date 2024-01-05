package haw.vs.model.matchmanager.state;

import haw.vs.common.PlayerConfigData;

import java.util.Objects;

public final class MenuEvent {
    private final Long playerId;
    private final Long matchId;
    private final Integer numberOfPlayers;
    private final PlayerConfigData configData;
    private final MenuEventType eventType;

    public MenuEvent(Long playerId, Long matchId, Integer numberOfPlayers, PlayerConfigData configData,
                     MenuEventType eventType) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.numberOfPlayers = numberOfPlayers;
        this.configData = configData;
        this.eventType = eventType;
    }

    public Long playerId() {
        return playerId;
    }

    public Long matchId() {
        return matchId;
    }

    public Integer numberOfPlayers() {
        return numberOfPlayers;
    }

    public PlayerConfigData configData() {
        return configData;
    }

    public MenuEventType eventType() {
        return eventType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (MenuEvent) obj;
        return Objects.equals(this.playerId, that.playerId) &&
                Objects.equals(this.matchId, that.matchId) &&
                Objects.equals(this.numberOfPlayers, that.numberOfPlayers) &&
                Objects.equals(this.configData, that.configData) &&
                Objects.equals(this.eventType, that.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, matchId, numberOfPlayers, configData, eventType);
    }

    @Override
    public String toString() {
        return "MenuEvent[" +
                "playerId=" + playerId + ", " +
                "matchId=" + matchId + ", " +
                "numberOfPlayers=" + numberOfPlayers + ", " +
                "configData=" + configData + ", " +
                "eventType=" + eventType + ']';
    }

}
