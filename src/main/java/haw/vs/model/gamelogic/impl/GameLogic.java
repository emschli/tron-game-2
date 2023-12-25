package haw.vs.model.gamelogic.impl;

import haw.vs.common.Coordinate;
import haw.vs.common.Direction;
import haw.vs.model.common.Match;
import haw.vs.model.common.Player;
import haw.vs.model.gamelogic.IGameLogic;
import haw.vs.model.gamelogic.IGameStateProcessedHandler;
import haw.vs.model.gamelogic.api.GameLogicFactory;

import java.util.List;

public class GameLogic implements IGameLogic {

    private IGameStateProcessedHandler gameStateProcessedHandler;
    private GameLogicFactory glf;

    public GameLogic() {
        this.glf = new GameLogicFactory();
        this.gameStateProcessedHandler = glf.getGameStateProcessedHandler();
    }

    @Override
    public void processMatch(Match match) {
        // process Game
        movePlayers(match);
        checkCollisions(match);
        //TODO killPlayers();
        //return to matchManager
        gameStateProcessedHandler.updateMatch(match);
    }

    //TODO - wo sollen Checks über MatchState stattfinden?

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
            // add coordinate to trace
            Coordinate newHead = getNextCoordinate(player.getNextDirection(), player.getHead());
            player.getTrace().add(newHead);

        }
    }

    /**
     * Calculates the next coordinate, depending on current head and direction
     *
     * @param goingTo - the direction a player is currently driving towards
     * @param head    - the coordinate at thr front of the trace
     * @return the new head
     */
    private Coordinate getNextCoordinate(Direction goingTo, Coordinate head) {
        return switch (goingTo) {
            case UP -> head.add(new Coordinate(0, 1)); // y++
            case DOWN -> head.add(new Coordinate(0, -1)); // y--
            case LEFT -> head.add(new Coordinate(-1, 0)); // x--
            case RIGHT -> head.add(new Coordinate(1, 0)); // x++
            //default -> new Coordinate(0, 0);
        };
    }

    /**
     * Checks if any of the players alive are collided with borders,
     * traces (their own or others) or had a head-on collision
     * @param match - the current match containing the players in question
     */
    //TODO entweder liste zurückgeben oder zum schluss kollisionen löschen
    private void checkCollisions(Match match) {
        // check if within boundaries
        List<Player> toKill = match.getAlivePlayers().stream()
                .filter(player -> !inBoundaries(player.getHead(), match.getMaxGridX(), match.getMaxGridY()))
                .toList();
        // check for collision with other players head-on
        // check for collision with other players trace

    }


    private boolean inBoundaries(Coordinate newHead, int maxX, int maxY) {
        return newHead.x >= 0 && newHead.x <= maxX && newHead.y >= 0 && newHead.y <= maxY;
    }

    public static void main(String[] args) {

        GameLogic gl = new GameLogic();

        Coordinate c11 = new Coordinate(1, 1);

        Coordinate neueKoord = gl.getNextCoordinate(Direction.RIGHT, c11);

        System.out.println(neueKoord);


    }

}
