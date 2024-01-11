package haw.vs.view;

import haw.vs.common.ICallee;
import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.IServerStub;
import haw.vs.view.api.IPlayerInputHandler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PlayerInputHandlerAppStubProvider implements ICallee, IPlayerInputHandler {

    private final IServerStub serverStub;
    private final IPlayerInputHandler playerInputHandler;

    public PlayerInputHandlerAppStubProvider(IServerStub serverStub, IPlayerInputHandler playerInputHandler) {
        this.serverStub = serverStub;
        this.playerInputHandler = playerInputHandler;
    }

    @Override
    public void register() throws NameServiceException {
        List<Method> methods = new ArrayList<>();
        try {
            methods.add(this.getClass().getMethod("onGameStart", int.class));
            methods.add(this.getClass().getMethod("onCancel"));
            methods.add(this.getClass().getMethod("onKeyPressed", String.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        serverStub.register(methods, this, MethodTypes.STATEFUL);
    }

    @Override
    public void onGameStart(int numOfPlayers) {
        playerInputHandler.onGameStart(numOfPlayers);
    }

    @Override
    public void onKeyPressed(String pressedKey) {
        playerInputHandler.onKeyPressed(pressedKey);
    }

    @Override
    public void onCancel() {
        playerInputHandler.onCancel();
    }
}
