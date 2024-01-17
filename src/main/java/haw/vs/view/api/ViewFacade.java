package haw.vs.view.api;

import haw.vs.common.Coordinate;
import haw.vs.common.GameState;
import haw.vs.view.javafx.TronViewAdapter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Offers methods to trigger different view appearences
 */
public class ViewFacade implements IViewFacade {
    private final IView tronViewAdapter;

    public ViewFacade() {
        this.tronViewAdapter = new TronViewAdapter();
    }

    @Override
    public void setPlayerId(long playerId) {

    }
    @Override
    public void setPlayerColor(String playerColor) {
        PlayerInfo.setColor(playerColor);
    }

    /**
     * Starts the game view
     * @param gameState
     */
    @Override
    public void startGameView(GameState gameState) {
        tronViewAdapter.hideOverlay();
    }

    /**
     * Draws all the bikes and their lines and head
     * @param gameState
     */
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

    /**
     * Updates the gameView a last time. Shows the overlay to tell the user, that he lost
     * @param gameState
     */
    @Override
    public void playerLostView(GameState gameState) {
        updateView(gameState);
        tronViewAdapter.showOverlay("looser");

    }
    /**
     * Updates the gameView a last time. Shows the overlay to tell the user, that he won
     * @param gameState
     */
    @Override
    public void playerWonView(GameState gameState) {
        updateView(gameState);
        tronViewAdapter.showOverlay("winner");
    }

    /**
     * Updates the Player Count Menu to show the player how many other players are waiting with them.
     * Their color is showed.
     * @param playerCount
     * @param targetPlayerCount
     */
    @Override
    public void updatePlayerCountViewView(Integer playerCount, Integer targetPlayerCount, String color) {
        tronViewAdapter.hideOverlay();
        PlayerInfo.setNoOfPlayers(targetPlayerCount);
        PlayerInfo.setActualNoOfPlayers(playerCount);
        PlayerInfo.setColor(color);

        tronViewAdapter.showOverlay("playerCount");
    }

    /**
     * Shows the overlay with the main menu.
     */
    @Override
    public void showMainMenuView() {
        tronViewAdapter.showOverlay("main");
    }

    /**
     * Sets the MatchId für the Player
     * @param matchId
     */
    @Override
    public void setMatchIdView(Long matchId) {
        PlayerInfo.setMatchId(matchId);
    }
}
