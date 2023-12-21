package haw.vs.model.matchmanager;

import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.model.common.Match;
import haw.vs.model.common.Player;

public class MatchUpdateHandler implements IMatchUpdateHandler {
    private IGameViewUpdate gameViewUpdate;
    private Matches matches;

    public MatchUpdateHandler(IGameViewUpdate gameViewUpdate, Matches matches) {
        this.gameViewUpdate = gameViewUpdate;
        this.matches = matches;
    }

    @Override
    public void updateView(Match match) {

    }

    @Override
    public void updateView(MenuEvent event) {
        switch (event.getEventType()){
            case ADD_PLAYER:
                addPlayer(event);
                break;
            case PLAYER_CANCELED:
                removePlayer(event);
                break;
            default:
                throw new IllegalArgumentException("Invalid Menu Event!");
        }
    }

    private void addPlayer(MenuEvent event) {
        Match match = matches.addPlayerToMatch(event.getPlayerId(), event.getNumberOfPlayers(), event.getConfigData());
        gameViewUpdate.setMatchId(event.getPlayerId(), match.getMatchId());

       updateWaitingScreen(match);

        // Check if Match is now full
        if (match.isFull()) {
            matches.moveMatchToRunningMatches(match);
        }
    }

    private void removePlayer(MenuEvent event) {
        Match match = matches.removePlayerFromMatch(event.getPlayerId(), event.getMatchId(), event.getNumberOfPlayers());
        if (match != null) {
            updateWaitingScreen(match);
        }
    }

    private void updateWaitingScreen(Match match) {
        int playerCount = match.getPlayers().size();
        int targetPlayerCount = match.getNumberOfPlayers();
        for (Player player : match.getPlayers()) {
            gameViewUpdate.updatePlayerCountView(player.getPlayerId(), playerCount, targetPlayerCount);
        }
    }
}
