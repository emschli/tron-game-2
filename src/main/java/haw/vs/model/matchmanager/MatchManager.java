package haw.vs.model.matchmanager;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.model.matchmanager.api.IMatchController;

public class MatchManager implements IMatchController {
    private Matches matches;

    public MatchManager(Matches matches) {
        this.matches = matches;
    }

    @Override
    public void addPlayerToMatch(long playerId, int numberOfPlayers, PlayerConfigData configData) {
        MenuEvent event = new MenuEvent(playerId, null, numberOfPlayers, configData, MenuEventType.ADD_PLAYER);
        matches.addMenuEvent(event);
    }

    @Override
    public void deletePlayerFromMatch(long playerId, long matchId, int numberOfPlayers) {
        MenuEvent event = new MenuEvent(playerId, matchId, numberOfPlayers, null, MenuEventType.PLAYER_CANCELED);
        matches.addMenuEvent(event);
    }

    @Override
    public void movePlayer(long playerId, long matchId, Direction direction) {

    }
}
