package haw.vs.model.matchmanager.viewupdate;

import haw.vs.common.IGameState;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.model.common.Match;
import haw.vs.model.common.MatchState;
import haw.vs.model.common.Player;
import haw.vs.model.common.PlayerState;
import haw.vs.model.matchmanager.state.GameStateCreator;
import haw.vs.model.matchmanager.state.Matches;
import haw.vs.model.matchmanager.state.MenuEvent;

import java.util.ArrayList;

public class MatchUpdateHandler implements IMatchUpdateHandler {
    private final IGameViewUpdate gameViewUpdate;
    private final Matches matches;

    public MatchUpdateHandler(IGameViewUpdate gameViewUpdate, Matches matches) {
        this.gameViewUpdate = gameViewUpdate;
        this.matches = matches;
    }

    @Override
    public void updateView(Match match) {
        switch (match.getState()){
            case READY -> startGame(match);
            case RUNNING -> updateRunningGame(match);
            case ENDED -> updateEndedGame(match);
            default -> throw new IllegalArgumentException("Invalid Match State!");
        }
    }

    @Override
    public void updateView(MenuEvent event) {
        switch (event.eventType()){
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

    private void startGame(Match match) {
        IGameState gameState = GameStateCreator.createGameState(match);
        for (Player player : match.getPlayers()) {
            player.setState(PlayerState.PLAYING);
            gameViewUpdate.startGame(player.getPlayerId(), gameState);
        }
        match.setState(MatchState.RUNNING);
    }

    private void updateRunningGame(Match match) {
        IGameState gameState = GameStateCreator.createGameState(match);
        for (Player player : new ArrayList<>(match.getPlayers())) {
            switch (player.getState()) {
                case DEAD -> {
                    gameViewUpdate.playerLost(player.getPlayerId(), gameState);
                    match.removePlayer(player);
                }
                case PLAYING -> gameViewUpdate.updateView(player.getPlayerId(), gameState);
            }
        }
    }

    private void updateEndedGame(Match match) {
        IGameState gameState = GameStateCreator.createGameState(match);
        for (Player player: match.getPlayers()) {
            switch (player.getState()) {
                case DEAD -> gameViewUpdate.playerLost(player.getPlayerId(), gameState);
                case WON -> gameViewUpdate.playerWon(player.getPlayerId(), gameState);
            }
        }

        matches.removeEndedMatch(match);
    }

    private void addPlayer(MenuEvent event) {
        Match match = matches.addPlayerToMatch(event.playerId(), event.numberOfPlayers(), event.configData());
        gameViewUpdate.setMatchId(event.playerId(), match.getMatchId());

       updateWaitingScreen(match);

        // Check if Match is now full
        if (match.isFull()) {
            matches.moveMatchToRunningMatches(match);
        }
    }

    private void removePlayer(MenuEvent event) {
        Match match = matches.removePlayerFromMatch(event.playerId(), event.matchId(), event.numberOfPlayers());
        gameViewUpdate.showMainMenu(event.playerId());
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
