package haw.vs.controller.appstub;

import haw.vs.common.IGameState;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.ICallee;
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

        List<String> methodNames = new ArrayList<>();
        methodNames.add("startGame");
        methodNames.add("updateView");
        methodNames.add("playerWon");
        methodNames.add("playerLost");
        methodNames.add("updatePlayerCountView");
        methodNames.add("showMainMenu");
        methodNames.add("setMatchId");
        this.serverStub.register(methodNames, this, MethodTypes.STATELESS);
    }

    @Override
    public void call(String methodName, Object[] args) {
        for (Method method : this.getClass().getMethods()) {
            if (method.getName().equals(methodName)) {
                try {
                    method.invoke(args);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void setId(long id) {
        // TODO??
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
