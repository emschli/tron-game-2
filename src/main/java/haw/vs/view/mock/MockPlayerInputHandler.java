package haw.vs.view.mock;

import haw.vs.view.api.IPlayerInputHandler;

public class MockPlayerInputHandler implements IPlayerInputHandler {
    @Override
    public void onGameStart() {
        System.out.println("PlayerInputHandler:   onGameStart()");
    }

    @Override
    public void onKeyPressed() {
        System.out.println("PlayerInputHandler:   onKeyPressed()");
    }

    @Override
    public void onCancel() {
        System.out.println("PlayerInputHandler:   onCancel()");
    }

    @Override
    public void onBackToMain() {
        System.out.println("PlayerInputHandler:   onBackToMain()");
    }
}
