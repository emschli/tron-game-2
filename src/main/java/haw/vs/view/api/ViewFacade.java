package haw.vs.view.api;

import haw.vs.common.Coordinate;
import haw.vs.common.GameState;
import haw.vs.view.javafx.TronViewAdapter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewFacade implements IViewFacade {
    private final IView tronViewAdapter;

    public ViewFacade() {
        this.tronViewAdapter = new TronViewAdapter();
    }

    @Override
    public void setPlayerId(long playerId) {

    }

    @Override
    public void startGameView(GameState gameState) {
        tronViewAdapter.hideOverlay();
    }

    @Override
    public void updateView(GameState gameState) {

        //all colors with their bikes
        Map<String, List<Coordinate>> gameStateMap = gameState.getPlayerPositionMap();
        // all colors
        List<String> playerColors = gameStateMap.keySet().stream().collect(Collectors.toList());
        // for very color, draw the bike
        for (int i = 0; i < playerColors.size(); i++) {
            tronViewAdapter.draw(gameStateMap.get(playerColors.get(i)), playerColors.get(i));
        }
        //highlight the heads
        //all bikes
        List<List<Coordinate>> allBikes = gameStateMap.values().stream().collect(Collectors.toList());
        //all heads
        for (int i = 0; i < allBikes.size(); i++) {
            int lenght = allBikes.get(i).size();
            Coordinate head = allBikes.get(i).get(lenght-1);
            //highlight them
            tronViewAdapter.highlightCell(head.x, head.y);
        }
    }

    @Override
    public void playerLostView(GameState gameState) {
        updateView(gameState);
        tronViewAdapter.showOverlay("looser");

    }

    @Override
    public void playerWonView(GameState gameState) {
        updateView(gameState);
        tronViewAdapter.showOverlay("winner");
    }

    @Override
    public void updatePlayerCountViewView(Integer playerCount, Integer targetPlayerCount) {
        tronViewAdapter.hideOverlay();
        PlayerInfo.setNoOfPlayers(targetPlayerCount);
        PlayerInfo.setActualNoOfPlayers(playerCount);
        tronViewAdapter.showOverlay("playerCount");
    }

    @Override
    public void showMainMenuView() {
        tronViewAdapter.showOverlay("main");
    }

    @Override
    public void setMatchIdView(Long matchId) {
        PlayerInfo.setMatchId(matchId);
    }
}
