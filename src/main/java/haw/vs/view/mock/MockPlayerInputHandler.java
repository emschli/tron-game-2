package haw.vs.view.mock;

import haw.vs.view.api.IPlayerInputHandler;

public class MockPlayerInputHandler implements IPlayerInputHandler {
    @Override
    public void onGameStart(int numOfPlayers) {
        System.out.println("PlayerInputHandler:   onGameStart()");
    }

    @Override
    public void onKeyPressed(String pressedKey) {
        System.out.println("PlayerInputHandler:   onKeyPressed()");
    }

    @Override
    public void onCancel() {
        System.out.println("PlayerInputHandler:   onCancel()");
    }

}
