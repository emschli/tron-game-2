package haw.vs.model.gamelogic.impl;

import haw.vs.common.Coordinate;
import haw.vs.common.Direction;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.model.common.Match;
import haw.vs.model.common.MatchState;
import haw.vs.model.common.Player;
import haw.vs.model.common.PlayerState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    private GameLogic gameLogic;
    private Match match1;
    private Match match2;

    @BeforeEach
    public void init() throws PropertiesException {
        PropertiesHelper.setPropertiesFile("model/gamelogic/game_logic_test.properties");
        gameLogic = new GameLogic(GameStateProcessedHandlerFactory.getGameStateProcessedHandler());

        Player zadie = new Player();
        zadie.setPlayerId(102983);
        zadie.setState(PlayerState.WAITING);
        zadie.setColor("amber");
        List<Coordinate> traceZad = new ArrayList<>();
        zadie.setTrace(traceZad);

        Player chimamanda = new Player();
        chimamanda.setPlayerId(231435);
        chimamanda.setState(PlayerState.WAITING);
        chimamanda.setColor("turquoise");
        List<Coordinate> traceChi = new ArrayList<>();
        chimamanda.setTrace(traceChi);


        Player mirjam = new Player();
        mirjam.setPlayerId(230583);
        mirjam.setState(PlayerState.PLAYING);
        mirjam.setColor("red");
        List<Coordinate> traceMir = new ArrayList<>();
        mirjam.setTrace(traceMir);

        Player natalie = new Player();
        natalie.setPlayerId(34524234);
        natalie.setState(PlayerState.PLAYING);
        natalie.setColor("blue");
        List<Coordinate> traceNat = new ArrayList<>();
        natalie.setTrace(traceNat);

        Player olga = new Player();
        olga.setPlayerId(2351900);
        olga.setState(PlayerState.PLAYING);
        olga.setColor("yellow");
        List<Coordinate> traceOlg = new ArrayList<>();
        olga.setTrace(traceOlg);

        Player rebecca = new Player();
        rebecca.setPlayerId(23452522);
        rebecca.setState(PlayerState.PLAYING);
        rebecca.setColor("green");
        List<Coordinate> traceReb = new ArrayList<>();
        rebecca.setTrace(traceReb);

        List<Player> twoTronPlayers = new ArrayList<>();
        twoTronPlayers.add(zadie);
        twoTronPlayers.add(chimamanda);

        List<Player> fourTronPlayers = new ArrayList<>();
        fourTronPlayers.add(mirjam);
        fourTronPlayers.add(natalie);
        fourTronPlayers.add(olga);
        fourTronPlayers.add(rebecca);

        match1 = new Match();
        match1.setMatchId(12345);
        match1.setState(MatchState.READY);
        match1.setNumberOfPlayers(2);
        match1.setMaxGridX(10);
        match1.setMaxGridY(10);
        match1.setPlayers(twoTronPlayers);

        match2 = new Match();
        match2.setMatchId(67890);
        match2.setState(MatchState.READY);
        match2.setNumberOfPlayers(4);
        match2.setMaxGridX(10);
        match2.setMaxGridY(10);
        match2.setPlayers(fourTronPlayers);
    }


    // Test positioning of players
    @Test
    void testProcessMatchWithReadyMatch() {
        gameLogic.processMatch(match1);
        assertEquals(1, match1.getPlayers().get(0).getTrace().size());
        assertEquals(1, match1.getPlayers().get(1).getTrace().size());
        assert (match1.getPlayers().get(0).getCurrentDirection() == Direction.DOWN);
        assert (match1.getPlayers().get(0).getNextDirection() == Direction.DOWN);
        assert (match1.getPlayers().get(1).getCurrentDirection() == Direction.UP);
        assert (match1.getPlayers().get(1).getNextDirection() == Direction.UP);
    }

    @Test
    void testProcessMatchDoingNothing() {
        gameLogic.processMatch(match2); // position Players
        match2.setState(MatchState.RUNNING); // normally done by MatchManager
        gameLogic.processMatch(match2);
        gameLogic.processMatch(match2);
        gameLogic.processMatch(match2);
        gameLogic.processMatch(match2);
        assertEquals(5, match2.getPlayers().get(0).getTrace().size());
        assertEquals(5, match2.getPlayers().get(1).getTrace().size());
        assertEquals(5, match2.getPlayers().get(2).getTrace().size());
        assertEquals(5, match2.getPlayers().get(3).getTrace().size());
        gameLogic.processMatch(match2); // nobody did anything, so everybody should crash in the middle
        assertEquals(0, match2.getAlivePlayers().size());
        assertEquals(PlayerState.DEAD, match2.getPlayers().get(0).getState());
        assertEquals(MatchState.ENDED, match2.getState());
    }

    @Test
    void testProcessMatchSetMatchState() {
        gameLogic.processMatch(match1); // position Players
        match1.setState(MatchState.RUNNING); // normally done by MatchManager
        gameLogic.processMatch(match1);
        match1.getPlayers().get(1).setState(PlayerState.DEAD); // one player alive in match
        gameLogic.processMatch(match1);
        assertEquals(MatchState.ENDED, match1.getState());
    }

    @Test
    void testProcessMatchSteering() {
        Player zadie = match1.getPlayers().get(0);
        Player chimamanda = match1.getPlayers().get(1);
        gameLogic.processMatch(match1); // position Players
        match1.setState(MatchState.RUNNING); // normally done by MatchManager
        zadie.setState(PlayerState.PLAYING); // normally done by MatchManager
        chimamanda.setState(PlayerState.PLAYING); // normally done by MatchManager

        gameLogic.processMatch(match1); // second
        zadie.setNextDirection(Direction.UP); // is reverse direction, should correct to current direction (down) and get on with it
        chimamanda.setNextDirection(Direction.RIGHT);
        gameLogic.processMatch(match1); // third
        assertEquals(Direction.DOWN, zadie.getCurrentDirection());
        assertEquals(Direction.RIGHT, chimamanda.getCurrentDirection());
        chimamanda.setNextDirection(Direction.DOWN);
        gameLogic.processMatch(match1); // fourth
        assertEquals(5, zadie.getHead().x);
        assertEquals(3, zadie.getHead().y);
        assertEquals(6, chimamanda.getHead().x);
        assertEquals(10, chimamanda.getHead().y);
        gameLogic.processMatch(match1); // fifth
        assertEquals(PlayerState.WON, zadie.getState());
        assertEquals(PlayerState.DEAD, chimamanda.getState());
        assertEquals(MatchState.ENDED, match1.getState());
    }


}