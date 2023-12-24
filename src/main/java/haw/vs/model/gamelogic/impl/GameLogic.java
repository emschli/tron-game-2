package haw.vs.model.gamelogic.impl;

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
        // return to matchManager
        gameStateProcessedHandler.updateMatch(match);
    }

    private void movePlayers(Match match) {
        // gehe durch die vorhandenen Player
        // nimm die direction
        // prüfe ob ok (nicht "zurück")
        // füge die Koordinate der geünschten Richtung der trace hinzu
    }
    private void checkCollisions(Match match) {
        // prüfe ob noch in boundaries
        // prüfe ob mit anderen playern kollidiert (sonderfall frontalzusammenstoß?)
    }



}
