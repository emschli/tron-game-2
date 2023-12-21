package haw.vs.model.matchmanager;

import haw.vs.common.PlayerConfigData;

public class MenuEvent {
    private Long playerId;
    private Long matchId;
    private Integer numberOfPlayers;
    private PlayerConfigData configData;
    private MenuEventType eventType;

    public MenuEvent(Long playerId, Long matchId, Integer numberOfPlayers, PlayerConfigData configData, MenuEventType eventType) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.numberOfPlayers = numberOfPlayers;
        this.configData = configData;
        this.eventType = eventType;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public PlayerConfigData getConfigData() {
        return configData;
    }

    public MenuEventType getEventType() {
        return eventType;
    }
}
