package haw.vs.view.api;

public interface IViewFacade {

    //TODO GameState Typ machen
    //public void startGame(GameState gameState);

    //TODO GameState Typ machen
    //public void update(GameState gameState);

    //TODO GameState Typ machen
    //public void playerLost(GameState gameState);

    //TODO GameState Typ machen
    //public void playerWon(GameState gameState);

    public void updatePlayerCountView(int playerCount, int targetPlayerCount);

    public void showMainMenu();

    public void setMatchId(long matchId);

}
