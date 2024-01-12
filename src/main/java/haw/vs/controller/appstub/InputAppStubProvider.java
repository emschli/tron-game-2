package haw.vs.controller.appstub;

import haw.vs.common.Direction;
import haw.vs.common.ICallee;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;
import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.IServerStub;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InputAppStubProvider implements IInput, ICallee {
    private final IServerStub serverStub;

    private final IInput input;

    public InputAppStubProvider(IServerStub serverStub, IInput input) {
        this.serverStub = serverStub;
        this.input = input;

    }

    @Override
    public void register() throws NameServiceException {
        List<Method> methods = new ArrayList<>();
        try {
            methods.add(this.getClass().getMethod("joinGameController", long.class, int.class, PlayerConfigData.class));
            methods.add(this.getClass().getMethod("cancelWaitController", long.class, long.class, int.class));
            methods.add(this.getClass().getMethod("handleGameActionController", long.class, long.class, Direction.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        serverStub.register(methods, this, MethodTypes.STATELESS);
    }



    @Override
    public void joinGameController(long playerId, int noOfPlayers, PlayerConfigData configData) {
        input.joinGameController(playerId, noOfPlayers, configData);
    }

    @Override
    public void cancelWaitController(long playerId, long matchId, int noOfPlayers) {
        input.cancelWaitController(playerId, matchId, noOfPlayers);
    }

    @Override
    public void handleGameActionController(long playerId, long matchId, Direction dir) {
        input.handleGameActionController(playerId, matchId, dir);
    }
}
