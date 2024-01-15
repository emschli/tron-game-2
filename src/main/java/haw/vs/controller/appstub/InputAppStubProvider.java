package haw.vs.controller.appstub;

import haw.vs.common.Direction;
import haw.vs.common.ICallee;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;
import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;
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
    public void register() throws NameServiceException, MethodNameAlreadyExistsException {
        List<Method> methods = new ArrayList<>();
        try {
            methods.add(this.getClass().getMethod("joinGameController", Long.class, Integer.class, PlayerConfigData.class));
            methods.add(this.getClass().getMethod("cancelWaitController", Long.class, Long.class, Integer.class));
            methods.add(this.getClass().getMethod("handleGameActionController", Long.class, Long.class, Direction.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e); //✅
        }
        serverStub.register(methods, this, MethodTypes.STATELESS);
    }



    @Override
    public void joinGameController(Long playerId, Integer noOfPlayers, PlayerConfigData configData) {
        input.joinGameController(playerId, noOfPlayers, configData);
    }

    @Override
    public void cancelWaitController(Long playerId, Long matchId, Integer noOfPlayers) {
        input.cancelWaitController(playerId, matchId, noOfPlayers);
    }

    @Override
    public void handleGameActionController(Long playerId, Long matchId, Direction dir) {
        input.handleGameActionController(playerId, matchId, dir);
    }
}
