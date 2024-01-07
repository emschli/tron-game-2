package haw.vs.controller.appstub;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

public class InputAppStubConsumer implements IInput {

    private IClientStub clientStub;

    public InputAppStubConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
    }
    public InputAppStubConsumer() {
    }
    @Override
    public void joinGame(long playerId, int noOfPlayers, PlayerConfigData configData) {
        Object[] args = new Object[] {
                playerId,
                noOfPlayers,
                configData
        };
        invoke("joinGame", args, ModeTypes.ASYNC_TCP);
    }

    @Override
    public void cancelWait(long playerId, long matchId, int noOfPlayers) {
        Object[] args = new Object[] {
                playerId,
                matchId,
                noOfPlayers
        };
        invoke("cancelWait", args, ModeTypes.ASYNC_TCP);
    }

    @Override
    public void handleGameAction(long playerId, long matchId, Direction dir) {
        Object[] args = new Object[] {
                playerId,
                matchId,
                dir
        };
        invoke("handleGameAction", args, ModeTypes.ASYNC_TCP);
    }

    private void invoke(String methodName, Object[] args, int mode) {
        try {
            clientStub.invoke(methodName, args, mode);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            throw new RuntimeException(e);
        }
    }
}
