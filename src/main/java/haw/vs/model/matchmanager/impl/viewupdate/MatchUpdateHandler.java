package haw.vs.model.matchmanager.impl.viewupdate;

import haw.vs.common.GameState;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.model.common.Match;
import haw.vs.model.common.Player;
import haw.vs.model.matchmanager.impl.state.GameStateCreator;
import haw.vs.model.matchmanager.impl.state.Matches;
import haw.vs.model.matchmanager.impl.state.MenuEvent;

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
            case READY:
                startGame(match);
                break;
            case RUNNING:
                updateRunningGame(match);
                break;
            case ENDED:
                updateEndedGame(match);
                break;
            default:
                System.out.println("Invalid Game State: " + match.getState());
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
        GameState gameState = GameStateCreator.createGameState(match);
        for (Player player : match.getPlayers()) {
            gameViewUpdate.startGameController(player.getPlayerId(), gameState);
        }
    }

    private void updateRunningGame(Match match) {
        GameState gameState = GameStateCreator.createGameState(match);
        for (Player player : match.getPlayers()) {
            switch (player.getState()) {
                case DEAD:
                    gameViewUpdate.playerLostController(player.getPlayerId(), gameState);
                    break;
                case PLAYING:
                    gameViewUpdate.updateController(player.getPlayerId(), gameState);
            }
        }
    }

    private void updateEndedGame(Match match) {
        GameState gameState = GameStateCreator.createGameState(match);
        for (Player player: match.getPlayers()) {
            switch (player.getState()) {
                case DEAD:
                    gameViewUpdate.playerLostController(player.getPlayerId(), gameState);
                    break;
                case WON:
                    gameViewUpdate.playerWonController(player.getPlayerId(), gameState);
            }
        }
    }

    private void addPlayer(MenuEvent event) {
        Match match = matches.addPlayerToMatch(event.playerId(), event.numberOfPlayers(), event.configData());
        gameViewUpdate.setMatchIdController(event.playerId(), match.getMatchId());

       updateWaitingScreen(match);

        // Check if Match is now full
        if (match.isFull()) {
            matches.moveMatchToRunningMatches(match);
        }
    }

    private void removePlayer(MenuEvent event) {
        Match match = matches.removePlayerFromMatchWaiting(event.playerId(), event.matchId(), event.numberOfPlayers());
        gameViewUpdate.showMainMenuController(event.playerId());
        if (match != null) {
            updateWaitingScreen(match);
        }
    }

    private void updateWaitingScreen(Match match) {
        int playerCount = match.getPlayers().size();
        int targetPlayerCount = match.getNumberOfPlayers();
        for (Player player : match.getPlayers()) {
            gameViewUpdate.updatePlayerCountViewController(player.getPlayerId(), playerCount, targetPlayerCount, player.getColor());
        }
    }
}
