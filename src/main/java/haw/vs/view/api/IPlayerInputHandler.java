package haw.vs.view.api;

public interface IPlayerInputHandler {

    public void onGameStart(int numOfPlayers);

    public void onKeyPressed(String pressedKey);

    public void onCancel();

    public void onBackToMain();
}