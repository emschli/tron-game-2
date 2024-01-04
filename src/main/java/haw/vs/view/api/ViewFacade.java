package haw.vs.view.api;

import haw.vs.common.Coordinate;
import haw.vs.common.GameState;
import haw.vs.view.javafx.TronViewAdapter;
import haw.vs.view.overlay.PlayerCountViewNew;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static haw.vs.view.javafx.TronView.tronView;

public class ViewFacade implements IViewFacade {
    private final IView tronViewAdapter;

    public ViewFacade() throws IOException {
        this.tronViewAdapter = new TronViewAdapter();
    }

    @Override
    public void startGame(GameState gameState) {

        tronViewAdapter.showOverlay("main");
    }

    @Override
    public void update(GameState gameState) {

        //all colors with their bikes
        Map<String, List<Coordinate>> gameStateMap = gameState.getPlayerPositionMap();
        // all colors
        List<String> playerColors = gameStateMap.keySet().stream().collect(Collectors.toList());
        // for very color, draw the bike
        for (int i = 0; i < playerColors.size(); i++) {
            tronViewAdapter.draw(gameStateMap.get(playerColors.get(i)), playerColors.get(i));
        }
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

        tronViewAdapter.showOverlay("main");
    }

    @Override
    public void setMatchId(long matchId) {
        PlayerInfo.setMatchId(matchId);
    }
}
