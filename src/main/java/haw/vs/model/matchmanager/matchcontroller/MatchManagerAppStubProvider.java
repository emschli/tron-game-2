package haw.vs.model.matchmanager.matchcontroller;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.ICallee;
import haw.vs.middleware.serverStub.api.IServerStub;
import haw.vs.model.matchmanager.api.IMatchController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MatchManagerAppStubProvider implements IMatchController, ICallee {
    private final IServerStub serverStub;

    private final IMatchController matchController;

    public MatchManagerAppStubProvider(IServerStub serverStub, IMatchController matchController) throws NameServiceException {
        this.serverStub = serverStub;
        this.matchController = matchController;

        List<String> methodNames = new ArrayList<>();
        methodNames.add("addPlayerToMatch");
        methodNames.add("deletePlayerFromMatch");
        methodNames.add("movePlayer");
        this.serverStub.register(methodNames, this, MethodTypes.STATEFUL);
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
