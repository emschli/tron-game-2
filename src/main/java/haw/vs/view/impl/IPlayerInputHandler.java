package haw.vs.view.impl;

public interface IPlayerInputHandler {

    public void onGameStart(int numOfPlayers);

    public void onKeyPressed(String pressedKey);

    public void onCancel();
}