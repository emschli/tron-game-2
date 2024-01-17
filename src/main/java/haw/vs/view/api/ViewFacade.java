package haw.vs.view.api;

import haw.vs.common.Coordinate;
import haw.vs.common.GameState;
import haw.vs.view.javafx.TronViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * @param gameState transfers all the game information
     */
    @Override
    public void startGameView(GameState gameState) {
        tronViewAdapter.hideOverlay();
    }

    /**
     * Draws all the bikes and their lines and head
     * @param gameState all the game information
     */
    @Override
    public void updateView(GameState gameState) {

        //all colors with their bikes
        Map<String, List<Coordinate>> gameStateMap = gameState.getPlayerPositionMap();
        // all colors
        List<String> playerColors = new ArrayList<>(gameStateMap.keySet());
        // for very color, draw the bike
        for (String playerColor : playerColors) {
            tronViewAdapter.draw(gameStateMap.get(playerColor), playerColor);
        }
        //highlight the heads
        //all bikes
        List<List<Coordinate>> allBikes = new ArrayList<>(gameStateMap.values());
        //all heads
        for (List<Coordinate> allBike : allBikes) {
            int lenght = allBike.size();
            Coordinate head = allBike.get(lenght - 1);
            //highlight them
            tronViewAdapter.highlightCell(head.x, head.y);
        }
    }

    /**
     * Updates the gameView a last time. Shows the overlay to tell the user, that he lost
     * @param gameState transfers all the game information
     */
    @Override
    public void playerLostView(GameState gameState) {
        updateView(gameState);
        tronViewAdapter.showOverlay("looser");

    }
    /**
     * Updates the gameView a last time. Shows the overlay to tell the user, that he won
     * @param gameState object to transfer all the game information
     */
    @Override
    public void playerWonView(GameState gameState) {
        updateView(gameState);
        tronViewAdapter.showOverlay("winner");
    }

    /**
     * Updates the Player Count Menu to show the player how many other players are waiting with them.
     * Their color is showed.
     * @param playerCount how many players are there already
     * @param targetPlayerCount target playercount
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
     * @param matchId match id which is to be setted
     */
    @Override
    public void setMatchIdView(Long matchId) {
        PlayerInfo.setMatchId(matchId);
    }
}
