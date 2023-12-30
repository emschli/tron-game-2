package haw.vs.view.api;

import haw.vs.common.GameState;
import haw.vs.view.javafx.TronViewAdapter;
import haw.vs.view.test.PlayerCountViewTest;

import static haw.vs.view.javafx.TronView.tronView;

public class ViewFacade implements IViewFacade {
    private final IView tronViewAdapter;

    public ViewFacade() {
        this.tronViewAdapter = new TronViewAdapter();
    }

    @Override
    public void startGame(GameState gameState) {

    }

    @Override
    public void update(GameState gameState) {

    }

    @Override
    public void playerLost(GameState gameState) {

    }

    @Override
    public void playerWon(GameState gameState) {

    }

    @Override
    public void updatePlayerCountView(int playerCount, int targetPlayerCount) {
        // Build and register playerCountView
        PlayerCountViewTest playerCountViewTest = new PlayerCountViewTest("menu.css", tronView);
        tronView.registerOverlay("count", playerCountViewTest);
        // init view and show start menu
        // tronView.init();
        tronView.showOverlay("count");

    }

    @Override
    public void showMainMenu() {

    }

    @Override
    public void setMatchId(long matchId) {

    }
}
