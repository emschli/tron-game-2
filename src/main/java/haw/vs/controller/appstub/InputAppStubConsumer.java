package haw.vs.controller.appstub;

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
    public InputAppStubConsumer() {
    }
    @Override
    public void joinGame(long playerId, int noOfPlayers, PlayerConfigData configData) {

        invoke("joinGame", ModeTypes.ASYNC_TCP, playerId, noOfPlayers,configData);
    }

    @Override
    public void cancelWait(long playerId, long matchId, int noOfPlayers) {
        invoke("cancelWait",ModeTypes.ASYNC_TCP, playerId, matchId, noOfPlayers);
    }

    @Override
    public void handleGameAction(long playerId, long matchId, Direction dir) {
        invoke("handleGameAction", ModeTypes.ASYNC_UDP, playerId, matchId, dir);
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
