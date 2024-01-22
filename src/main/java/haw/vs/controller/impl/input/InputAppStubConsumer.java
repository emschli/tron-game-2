package haw.vs.controller.impl.input;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;
import haw.vs.middleware.ModeTypes;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

public class InputAppStubConsumer implements IInput {

    private IClientStub clientStub;

    public InputAppStubConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
    }

    @Override
    public void joinGameController(Long playerId, Integer noOfPlayers, PlayerConfigData configData) {
        invoke("joinGameController", ModeTypes.ASYNC_TCP, playerId, noOfPlayers,configData);
    }

    @Override
    public void cancelWaitController(Long playerId, Long matchId, Integer noOfPlayers) {
        invoke("cancelWaitController",ModeTypes.ASYNC_TCP, playerId, matchId, noOfPlayers);
    }

    @Override
    public void handleGameActionController(Long playerId, Long matchId, Direction dir) {
        invoke("handleGameActionController", ModeTypes.ASYNC_UDP, playerId, matchId, dir);
    }

    private void invoke(String methodName, int mode, Object... args) {
        try {
            clientStub.invoke(methodName, mode, args);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            throw new RuntimeException(e);
        }
    }
}
