package haw.vs.view.api;

import haw.vs.common.Coordinate;
import haw.vs.common.GameState;
import haw.vs.view.javafx.TronViewAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        PlayerInfo.setNoOfPlayers(targetPlayerCount);
        PlayerInfo.setActualNoOfPlayers(playerCount);
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
