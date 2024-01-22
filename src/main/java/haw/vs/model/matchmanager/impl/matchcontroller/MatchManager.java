package haw.vs.model.matchmanager.impl.matchcontroller;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.model.matchmanager.api.IMatchController;
import haw.vs.model.matchmanager.impl.state.Matches;
import haw.vs.model.matchmanager.impl.state.MenuEvent;
import haw.vs.model.matchmanager.impl.state.MenuEventType;

public class MatchManager implements IMatchController {
    private final Matches matches;

    public MatchManager(Matches matches) {
        this.matches = matches;
    }

    @Override
    public void addPlayerToMatchMatchManager(Long playerId, Integer numberOfPlayers, PlayerConfigData configData) {
        MenuEvent event = new MenuEvent(playerId, null, numberOfPlayers, configData, MenuEventType.ADD_PLAYER);
        matches.addMenuEvent(event);
    }

    @Override
    public void deletePlayerFromMatchMatchManager(Long playerId, Long matchId, Integer numberOfPlayers) {
        MenuEvent event = new MenuEvent(playerId, matchId, numberOfPlayers, null, MenuEventType.PLAYER_CANCELED);
        matches.addMenuEvent(event);
    }

    @Override
    public void movePlayerMatchManager(Long playerId, Long matchId, Direction direction) {
        matches.makePlayerMove(playerId, matchId, direction);
    }
}
