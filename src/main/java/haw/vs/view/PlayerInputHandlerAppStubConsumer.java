package haw.vs.view;

import haw.vs.middleware.ModeTypes;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.view.api.IPlayerInputHandler;

public class PlayerInputHandlerAppStubConsumer implements IPlayerInputHandler {

    private final IClientStub clientStub;

    public PlayerInputHandlerAppStubConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
    }

    @Override
    public void onGameStart(int numOfPlayers) {
        invoke("onGameStart", ModeTypes.ASYNC_TCP, numOfPlayers);
    }

    @Override
    public void onKeyPressed(String pressedKey) {
        invoke("onKeyPressed", ModeTypes.ASYNC_UDP, pressedKey);
    }

    @Override
    public void onCancel() {
        invoke("onCancel", ModeTypes.ASYNC_TCP);
    }

    private void invoke(String methodName, int mod, Object... args) {
        try {
            clientStub.invoke(methodName, mod, args);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            throw new RuntimeException(e);
        }
    }
}
