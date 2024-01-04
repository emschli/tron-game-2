package haw.vs.view.api;

import haw.vs.common.GameState;
import haw.vs.view.javafx.TronViewAdapter;
import haw.vs.view.overlay.MainMenuNew;
import haw.vs.view.overlay.PlayerCountViewNew;

import static haw.vs.view.javafx.TronView.tronView;

public class ViewFacade implements IViewFacade {
    private final IView tronViewAdapter;

    public ViewFacade() {
        this.tronViewAdapter = new TronViewAdapter();
    }

    @Override
    public void startGame(GameState gameState) {
//TODO ja hier ist noch was zu tun
    }

    @Override
    public void update(GameState gameState) {

    }

    @Override
    public void playerLost(GameState gameState) {
        //TODO gameState ist hier überflüssig oder?
        tronViewAdapter.showOverlay("looser");

    }

    @Override
    public void playerWon(GameState gameState) {
        //TODO gameState ist hier überflüssig oder?
        tronViewAdapter.showOverlay("winner");
    }

    @Override
    public void updatePlayerCountView(int playerCount, int targetPlayerCount) {
        PlayerCountViewNew playerCountView = new PlayerCountViewNew("playerCount", tronView, playerCount, targetPlayerCount);
        tronView.registerOverlay("playerCount", playerCountView);
        tronViewAdapter.showOverlay("playerCount");
    }

    @Override
    public void showMainMenu() {
        MainMenuNew mainMenu = new MainMenuNew("main", tronView);
        tronView.registerOverlay("main", mainMenu);
        tronViewAdapter.showOverlay("main");
    }

    @Override
    public void setMatchId(long matchId) {
        //TODO ??? wo ist denn die PlayerInfo überhaupt? muss die view das machen?
    }
}
