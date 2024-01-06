package haw.vs.controller.appstub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haw.vs.common.GameState;
import haw.vs.common.IGameState;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.middleware.serverStub.api.ICallee;

public class ApplicationStubGameViewUpdateProvider implements ICallee {
    private long id;
    IGameViewUpdate iGameViewUpdate;


    public ApplicationStubGameViewUpdateProvider(long id, IGameViewUpdate iGameViewUpdate) {
        this.id = id;
        this.iGameViewUpdate = iGameViewUpdate;
    }

    public ApplicationStubGameViewUpdateProvider(long id) {
        this.id = id;
    }

    @Override
    public void call(String methodName, Object[] args) {
        switch (methodName) {
            case "startGame":
                long startGamePlayerId = (long) args[0];
                String startGameStateString = (String) args[1];
                IGameState startGameState = convertGameStateToObject(startGameStateString);
                iGameViewUpdate.startGame(startGamePlayerId, startGameState);
                break;

            case "updateView":
                long updateViewPlayerId = (long) args[0];
                String updateViewGameStateString = (String) args[1];
                IGameState updateViewGameState = convertGameStateToObject(updateViewGameStateString);
                iGameViewUpdate.updateView(updateViewPlayerId, updateViewGameState);
                break;

            case "playerWon":
                long playerWonPlayerId = (long) args[0];
                String playerWonGameStateString = (String) args[1];
                IGameState playerWonGameState = convertGameStateToObject(playerWonGameStateString);
                iGameViewUpdate.playerWon(playerWonPlayerId, playerWonGameState);
                break;

            case "playerLost":
                long playerLostPlayerId = (long) args[0];
                String playerLostGameStateString = (String) args[1];
                IGameState playerLostGameState = convertGameStateToObject(playerLostGameStateString);
                iGameViewUpdate.playerLost(playerLostPlayerId, playerLostGameState);
                break;

            case "updatePlayerCountView":
                long updatePlayerCountViewPlayerId = (long) args[0];
                int playerCount = (int) args[1];
                int targetPlayerCount = (int) args[2];
                iGameViewUpdate.updatePlayerCountView(updatePlayerCountViewPlayerId, playerCount, targetPlayerCount);
                break;

            case "showMainMenu":
                long showMainMenuPlayerId = (long) args[0];
                iGameViewUpdate.showMainMenu(showMainMenuPlayerId);
                break;

            case "setMatchId":
                long setMatchIdPlayerId = (long) args[0];
                long matchId = (long) args[1];
                iGameViewUpdate.setMatchId(setMatchIdPlayerId, matchId);
                break;

            default:
                // Fehlerbehandlung
                break;
        }
    }


    private GameState convertGameStateToObject(String gameStateString) {
        ObjectMapper objectMapper = new ObjectMapper();
        GameState gameState = null;
        try {
            gameState = objectMapper.readValue(gameStateString, GameState.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return gameState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
