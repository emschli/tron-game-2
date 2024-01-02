package haw.vs.view.api;

import haw.vs.common.GameState;
import haw.vs.view.javafx.TronViewAdapter;
import haw.vs.view.overlay.PlayerCountViewNew;

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

    }

    @Override
    public void setMatchId(long matchId) {

    }
}
