package haw.vs.model.gamelogic.impl;

import haw.vs.common.Coordinate;
import haw.vs.common.Direction;
import haw.vs.model.common.Match;
import haw.vs.model.common.MatchState;
import haw.vs.model.common.Player;
import haw.vs.model.common.PlayerState;
import haw.vs.model.gamelogic.IGameLogic;
import haw.vs.model.gamelogic.api.IGameStateProcessedHandler;
import haw.vs.model.gamelogic.api.GameStateProcessedHandlerFactory;

import java.util.*;

public class GameLogic implements IGameLogic {

    private IGameStateProcessedHandler gameStateProcessedHandler;
    private GameStateProcessedHandlerFactory gameStateProcessedHandlerFactory;

    public GameLogic() {
        this.gameStateProcessedHandlerFactory = new GameStateProcessedHandlerFactory();
        this.gameStateProcessedHandler = gameStateProcessedHandlerFactory.getGameStateProcessedHandler();
    }

    @Override
    public void processMatch(Match match) {
        if (match.getState().equals(MatchState.READY)) {
            positionPlayersForStart(match);
        } else {
            movePlayers(match);
            manageCollisions(match);
            checkMatchState(match);
        }
        gameStateProcessedHandler.updateMatch(match);
    }

    /**
     * Sets the first coordinate in players trace and their directions so the match can beginn.
     *
     * @param match
     */
    private void positionPlayersForStart(Match match) {
        List<Player> players = match.getPlayers();
        int numberOfPlayers = match.getNumberOfPlayers();

        Player p1 = players.get(0);
        p1.getTrace().add(new Coordinate(match.getMaxGridX() / 2, 0));
        p1.setCurrentDirection(Direction.DOWN);
        p1.setNextDirection(Direction.DOWN);

        Player p2 = players.get(1);
        p2.getTrace().add(new Coordinate(match.getMaxGridX() / 2, match.getMaxGridY()));
        p2.setCurrentDirection(Direction.UP);
        p2.setNextDirection(Direction.UP);

        if (numberOfPlayers >= 3) {
            Player p3 = players.get(2);
            p3.getTrace().add(new Coordinate(0, match.getMaxGridY() / 2));
            p3.setCurrentDirection(Direction.RIGHT);
            p3.setNextDirection(Direction.RIGHT);
        }

        if (numberOfPlayers == 4) {
            Player p4 = players.get(3);
            p4.getTrace().add(new Coordinate(match.getMaxGridX(), match.getMaxGridY() / 2));
            p4.setCurrentDirection(Direction.LEFT);
            p4.setNextDirection(Direction.LEFT);
        }
    }

    /**
     * Takes the nextDirection of the players and adds them to their trace.
     *
     * @param match
     */
    private void movePlayers(Match match) {
        for (Player player : match.getAlivePlayers()) {
            // check NextDirection
            if (player.getNextDirection() == player.getCurrentDirection().backwards()) {
                player.setNextDirection(player.getCurrentDirection());
            }
            // add coordinate to trace and set current direction
            Coordinate newHead = getNextCoordinate(player.getNextDirection(), player.getHead());
            player.getTrace().add(newHead);
            player.setCurrentDirection(player.getNextDirection());
        }
    }

    /**
     * Calculates the next coordinate, depending on current head and direction
     *
     * @param going - the direction a player is currently driving towards
     * @param head  - the coordinate at the front of the trace
     * @return the new head
     */
    private Coordinate getNextCoordinate(Direction going, Coordinate head) {
        return switch (going) {
            case UP -> head.add(new Coordinate(0, -1)); // y--
            case DOWN -> head.add(new Coordinate(0, 1)); // y++
            case LEFT -> head.add(new Coordinate(-1, 0)); // x--
            case RIGHT -> head.add(new Coordinate(1, 0)); // x++
            //default -> new Coordinate(0, 0);
        };
    }

    /**
     * Checks if any of the players alive are collided with borders,
     * traces (their own or others) or had a head-on collision and kills them.
     *
     * @param match - the current match containing the players in question
     */
    private void manageCollisions(Match match) {
        Set<Player> toKill = new HashSet<>();

        for (Player player : match.getAlivePlayers()) {
            Coordinate playerHead = player.getHead();
            boolean isOutOfBoundaries = !inBoundaries(playerHead, match.getMaxGridX(), match.getMaxGridY());

            // Check if player is out of boundaries
            if (isOutOfBoundaries || isCollidingWithOwnTrace(player)) {
                toKill.add(player);
                continue; // Move to the next player
            }

            // Check for collision with other players' traces
            for (Player otherPlayer : match.getAlivePlayers()) {
                if (otherPlayer != player && isColliding(playerHead, otherPlayer.getTrace())) {
                    toKill.add(player);
                    break; // Move to the next player after adding to toKill
                }
            }
        }
        killPlayers(toKill);
    }

    /**
     * Sets the player state of a given set of players to dead
     *
     * @param toKill - the set of players to be killed
     */
    private void killPlayers(Set<Player> toKill) {
        for (Player p : toKill) {
            p.setState(PlayerState.DEAD);
        }
    }

    /**
     * Checks if a player is colliding with their own trace.
     *
     * @param player - player to be checked
     * @return true, if player is collided with own trace, false else
     */
    private boolean isCollidingWithOwnTrace(Player player) {
        return player.getTrace().subList(0, player.getTrace().size() - 1).contains(player.getHead());
    }

    /**
     * Checks if the head is colliding with a given trace
     *
     * @param head  - last element of the player
     * @param trace - the trace of another player in the match
     * @return true if collision took place, false else
     */
    private boolean isColliding(Coordinate head, List<Coordinate> trace) {
        return trace.contains(head);
    }

    /**
     * Checks, if player is driving out of the gridbounds.
     *
     * @param newHead - the coordinate added to the trace last
     * @param maxX
     * @param maxY
     * @return true, if player is driving within the boundaries, false else.
     */
    private boolean inBoundaries(Coordinate newHead, int maxX, int maxY) {
        return newHead.x >= 0 && newHead.x <= maxX && newHead.y >= 0 && newHead.y <= maxY;
    }

    /**
     * Checks if a match has ended and sets MatchState and PlayerState accordingly.
     *
     * @param match
     */
    private void checkMatchState(Match match) {
        int alivePlayerCount = match.getAlivePlayers().size();
        if (alivePlayerCount == 1) {
            match.getAlivePlayers().get(0).setState(PlayerState.WON);
        }
        if (alivePlayerCount <= 1) {
            match.setState(MatchState.ENDED);
        }
    }

    public static void main(String[] args) {

        GameLogic gl = new GameLogic();

    }

}
