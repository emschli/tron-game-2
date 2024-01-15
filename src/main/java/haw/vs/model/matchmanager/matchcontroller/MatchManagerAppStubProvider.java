package haw.vs.model.matchmanager.matchcontroller;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.common.ICallee;
import haw.vs.middleware.serverStub.api.IServerStub;
import haw.vs.model.matchmanager.api.IMatchController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MatchManagerAppStubProvider implements IMatchController, ICallee {
    private final IServerStub serverStub;

    private final IMatchController matchController;

    public MatchManagerAppStubProvider(IServerStub serverStub, IMatchController matchController) {
        this.serverStub = serverStub;
        this.matchController = matchController;
    }

    @Override
    public void register() throws NameServiceException {
        List<Method> methods = new ArrayList<>();
        try {
            methods.add(this.getClass().getMethod("addPlayerToMatchMatchManager", Long.class, Integer.class, PlayerConfigData.class));
            methods.add(this.getClass().getMethod("deletePlayerFromMatchMatchManager", Long.class, Long.class, Integer.class));
            methods.add(this.getClass().getMethod("movePlayerMatchManager", Long.class, Long.class, Direction.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e); //✅
        }
        try {
            serverStub.register(methods, this, MethodTypes.STATEFUL);
        } catch (haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException e) {
            throw new RuntimeException(e); //✅
        }
    }

    @Override
    public void addPlayerToMatchMatchManager(Long playerId, Integer numberOfPlayers, PlayerConfigData configData) {
        matchController.addPlayerToMatchMatchManager(playerId, numberOfPlayers, configData);
    }

    @Override
    public void deletePlayerFromMatchMatchManager(Long playerId, Long matchId, Integer numberOfPlayers) {
        matchController.deletePlayerFromMatchMatchManager(playerId, matchId, numberOfPlayers);
    }

    @Override
    public void movePlayerMatchManager(Long playerId, Long matchId, Direction direction) {
        matchController.movePlayerMatchManager(playerId, matchId, direction);
    }
}
