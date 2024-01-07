package haw.vs.model.matchmanager.matchcontroller;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.ICallee;
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
            methods.add(this.getClass().getMethod("addPlayerToMatch", long.class, int.class, PlayerConfigData.class));
            methods.add(this.getClass().getMethod("deletePlayerFromMatch", long.class, long.class, int.class));
            methods.add(this.getClass().getMethod("movePlayer", long.class, long.class, Direction.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        serverStub.register(methods, this, MethodTypes.STATEFUL);
    }

    @Override
    public void setId(long id) {

    }

    @Override
    public void addPlayerToMatch(long playerId, int numberOfPlayers, PlayerConfigData configData) {
        matchController.addPlayerToMatch(playerId, numberOfPlayers, configData);
    }

    @Override
    public void deletePlayerFromMatch(long playerId, long matchId, int numberOfPlayers) {
        matchController.deletePlayerFromMatch(playerId, matchId, numberOfPlayers);
    }

    @Override
    public void movePlayer(long playerId, long matchId, Direction direction) {
        matchController.movePlayer(playerId, matchId, direction);
    }
}
