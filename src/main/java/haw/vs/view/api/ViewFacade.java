package haw.vs.view.api;

import haw.vs.common.GameState;
import haw.vs.view.javafx.TronViewAdapter;

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
        //tronViewAdapter.showOverlay("count");
        // Build and register playerCountView
        //PlayerCountView playerCountView = new PlayerCountView("menu.css", tronView);
        //tronView.registerOverlay("count", playerCountView);
        // init view and show start menu
        // tronView.init();
        //tronView.showOverlay("count");

    }

    @Override
    public void showMainMenu() {

    }

    @Override
    public void setMatchId(long matchId) {

    }
}
