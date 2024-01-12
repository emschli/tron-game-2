package haw.vs.controller.appstub;

import haw.vs.common.*;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.IServerStub;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GameViewUpdateAppStubProvider implements IGameViewUpdate, ICallee {

    private IServerStub serverStub;

    private IGameViewUpdate gameViewUpdate;

    public GameViewUpdateAppStubProvider(IServerStub serverStub, IGameViewUpdate gameViewUpdate) {
        this.serverStub = serverStub;
        this.gameViewUpdate = gameViewUpdate;
    }

    @Override
    public void register() throws NameServiceException {
        List<Method> methods = new ArrayList<>();
        try {
            methods.add(this.getClass().getMethod("startGameController", long.class, GameState.class));
            methods.add(this.getClass().getMethod("updateController", long.class, GameState.class));
            methods.add(this.getClass().getMethod("playerWonController", long.class, GameState.class));
            methods.add(this.getClass().getMethod("playerLostController", long.class, GameState.class));
            methods.add(this.getClass().getMethod("updatePlayerCountViewController", long.class, int.class, int.class));
            methods.add(this.getClass().getMethod("showMainMenuController", long.class));
            methods.add(this.getClass().getMethod("setMatchIdController", long.class, long.class));

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        serverStub.register(methods, this, MethodTypes.STATELESS);
    }


    @Override
    public void startGameController(long playerId, GameState gameState) {
        gameViewUpdate.startGameController(playerId, gameState);
    }

    @Override
    public void updateController(long playerId, GameState gameState) {
        gameViewUpdate.updateController(playerId, gameState);
    }

    @Override
    public void playerWonController(long playerId, GameState gameState) {
        gameViewUpdate.playerWonController(playerId, gameState);
    }

    @Override
    public void playerLostController(long playerId, GameState gameState) {
        gameViewUpdate.playerLostController(playerId, gameState);
    }

    @Override
    public void updatePlayerCountViewController(long playerId, int playerCount, int targetPlayerCount) {
        gameViewUpdate.updatePlayerCountViewController(playerId, playerCount, targetPlayerCount);
    }

    @Override
    public void showMainMenuController(long playerId) {
        gameViewUpdate.showMainMenuController(playerId);
    }

    @Override
    public void setMatchIdController(long playerId, long matchId) {
        gameViewUpdate.setMatchIdController(playerId, matchId);
    }

}
