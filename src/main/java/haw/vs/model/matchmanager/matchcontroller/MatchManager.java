package haw.vs.model.matchmanager.matchcontroller;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.model.matchmanager.api.IMatchController;
import haw.vs.model.matchmanager.state.Matches;
import haw.vs.model.matchmanager.state.MenuEvent;
import haw.vs.model.matchmanager.state.MenuEventType;

public class MatchManager implements IMatchController {
    private final Matches matches;

    public MatchManager(Matches matches) {
        this.matches = matches;
    }

    @Override
    public void addPlayerToMatchMatchManager(long playerId, int numberOfPlayers, PlayerConfigData configData) {
        MenuEvent event = new MenuEvent(playerId, null, numberOfPlayers, configData, MenuEventType.ADD_PLAYER);
        matches.addMenuEvent(event);
    }

    @Override
    public void deletePlayerFromMatchMatchManager(long playerId, long matchId, int numberOfPlayers) {
        MenuEvent event = new MenuEvent(playerId, matchId, numberOfPlayers, null, MenuEventType.PLAYER_CANCELED);
        matches.addMenuEvent(event);
    }

    @Override
    public void movePlayerMatchManager(long playerId, long matchId, Direction direction) {
        matches.makePlayerMove(playerId, matchId, direction);
    }
}
