package haw.vs.controller.appstub;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.ICallee;
import haw.vs.middleware.serverStub.api.IServerStub;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InputAppStubProvider implements IInput, ICallee {
    private final IServerStub serverStub;

    private final IInput input;

    public InputAppStubProvider(IServerStub serverStub, IInput input) throws NameServiceException {
        this.serverStub = serverStub;
        this.input = input;

        List<String> methodNames = new ArrayList<>();
        methodNames.add("joinGame");
        methodNames.add("cancelWait");
        methodNames.add("handleGameAction");
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
        // Implement if needed
    }

    @Override
    public void joinGame(long playerId, int noOfPlayers, PlayerConfigData configData) {
        input.joinGame(playerId, noOfPlayers, configData);
    }

    @Override
    public void cancelWait(long playerId, long matchId, int noOfPlayers) {
        input.cancelWait(playerId, matchId, noOfPlayers);
    }

    @Override
    public void handleGameAction(long playerId, long matchId, Direction dir) {
        input.handleGameAction(playerId, matchId, dir);
    }
}
