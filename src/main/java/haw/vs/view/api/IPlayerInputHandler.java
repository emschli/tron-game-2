package haw.vs.view.api;

public interface IPlayerInputHandler {

    public void onGameStart();

    public void onKeyPressed(String pressedKey);

    public void onCancel();

    public void onBackToMain();
}