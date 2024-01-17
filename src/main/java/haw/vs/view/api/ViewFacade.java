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
     * Starts the game view -> start the counter to show the color -> the overlay hides when counter is ready
     * @param gameState
     */
    @Override
    public void startGameView(GameState gameState) {
        tronViewAdapter.hideOverlay();
    }

    /**
     * Draws all the bikes and their lines and highlights their heads.
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
        //highlight the head
        //users bike
        int length = gameStateMap.get(PlayerInfo.getColor()).size();
        //users head
        Coordinate myHead = gameStateMap.get(PlayerInfo.getColor()).get(length - 1);
        //highligt
        tronViewAdapter.highlightCell(myHead.x,myHead.y);
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
     * @param playerCount
     * @param targetPlayerCount
     */
    @Override
    public void updatePlayerCountViewView(Integer playerCount, Integer targetPlayerCount, String color) {
        tronViewAdapter.hideOverlay();
        System.out.println("Color: " + color);
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
