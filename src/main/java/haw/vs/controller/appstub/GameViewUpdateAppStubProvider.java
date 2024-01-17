package haw.vs.controller.appstub;

import haw.vs.common.*;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;
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
    public void register() throws NameServiceException, MethodNameAlreadyExistsException {
        List<Method> methods = new ArrayList<>();
        try {
            methods.add(this.getClass().getMethod("startGameController", Long.class, GameState.class));
            methods.add(this.getClass().getMethod("updateController", Long.class, GameState.class));
            methods.add(this.getClass().getMethod("playerWonController", Long.class, GameState.class));
            methods.add(this.getClass().getMethod("playerLostController", Long.class, GameState.class));
            methods.add(this.getClass().getMethod("updatePlayerCountViewController", Long.class, Integer.class, Integer.class, String.class));
            methods.add(this.getClass().getMethod("showMainMenuController", Long.class));
            methods.add(this.getClass().getMethod("setMatchIdController", Long.class, Long.class));

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        serverStub.register(methods, this, MethodTypes.STATELESS);
    }


    @Override
    public void startGameController(Long playerId, GameState gameState) {
        gameViewUpdate.startGameController(playerId, gameState);
    }

    @Override
    public void updateController(Long playerId, GameState gameState) {
        gameViewUpdate.updateController(playerId, gameState);
    }

    @Override
    public void playerWonController(Long playerId, GameState gameState) {
        gameViewUpdate.playerWonController(playerId, gameState);
    }

    @Override
    public void playerLostController(Long playerId, GameState gameState) {
        gameViewUpdate.playerLostController(playerId, gameState);
    }

    @Override
    public void updatePlayerCountViewController(Long playerId, Integer playerCount, Integer targetPlayerCount, String color) {
        gameViewUpdate.updatePlayerCountViewController(playerId, playerCount, targetPlayerCount, color);
    }

    @Override
    public void showMainMenuController(Long playerId) {
        gameViewUpdate.showMainMenuController(playerId);
    }

    @Override
    public void setMatchIdController(Long playerId, Long matchId) {
        gameViewUpdate.setMatchIdController(playerId, matchId);
    }

}
