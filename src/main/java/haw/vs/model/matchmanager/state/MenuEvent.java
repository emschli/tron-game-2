package haw.vs.model.matchmanager.state;

import haw.vs.common.PlayerConfigData;

public record MenuEvent(Long playerId, Long matchId, Integer numberOfPlayers, PlayerConfigData configData,
                        MenuEventType eventType) {
}
