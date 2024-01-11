package haw.vs.controller.appstub;

import haw.vs.common.*;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.IServerStub;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GameViewUpdateAppStubProvider implements IGameViewUpdate, ICallee {

    private IServerStub serverStub;

    private IGameViewUpdate gameViewUpdate;

    public GameViewUpdateAppStubProvider(IServerStub serverStub, IGameViewUpdate gameViewUpdate) throws NameServiceException {
        this.serverStub = serverStub;
        this.gameViewUpdate = gameViewUpdate;
    }

    @Override
    public void register() throws NameServiceException {
        List<Method> methods = new ArrayList<>();
        try {
            methods.add(this.getClass().getMethod("startGame", long.class, GameState.class));
            methods.add(this.getClass().getMethod("updateView", long.class, GameState.class));
            methods.add(this.getClass().getMethod("playerWon", long.class, GameState.class));
            methods.add(this.getClass().getMethod("playerLost", long.class, GameState.class));
            methods.add(this.getClass().getMethod("updatePlayerCountView", long.class, int.class, int.class));
            methods.add(this.getClass().getMethod("showMainMenu", long.class));
            methods.add(this.getClass().getMethod("setMatchId", long.class, long.class));

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        serverStub.register(methods, this, MethodTypes.STATELESS);
    }


    @Override
    public void startGame(long playerId, IGameState gameState) {
        gameViewUpdate.startGame(playerId, gameState);
    }

    @Override
    public void updateView(long playerId, IGameState gameState) {
        gameViewUpdate.updateView(playerId, gameState);
    }

    @Override
    public void playerWon(long playerId, IGameState gameState) {
        gameViewUpdate.playerWon(playerId, gameState);
    }

    @Override
    public void playerLost(long playerId, IGameState gameState) {
        gameViewUpdate.playerLost(playerId, gameState);
    }

    @Override
    public void updatePlayerCountView(long playerId, int playerCount, int targetPlayerCount) {
        gameViewUpdate.updatePlayerCountView(playerId, playerCount, targetPlayerCount);
    }

    @Override
    public void showMainMenu(long playerId) {
        gameViewUpdate.showMainMenu(playerId);
    }

    @Override
    public void setMatchId(long playerId, long matchId) {
        gameViewUpdate.setMatchId(playerId, matchId);
    }

}
