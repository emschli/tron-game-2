package haw.vs.model.gamelogic;

import haw.vs.common.Coordinate;
import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.model.common.Match;
import haw.vs.model.common.MatchState;
import haw.vs.model.common.Player;
import haw.vs.model.common.PlayerState;
import haw.vs.model.gamelogic.impl.GameLogic;


import java.util.ArrayList;
import java.util.List;

public class GameLogicTest {
    public static void main(String[] args) {

        Player mirjam = new Player();
        mirjam.setPlayerId(230583);
        mirjam.setState(PlayerState.PLAYING);
        mirjam.setColor("red - mirjam");
        List<Coordinate> traceMir = new ArrayList<>();
        mirjam.setTrace(traceMir);

        Player natalie = new Player();
        natalie.setPlayerId(34524234);
        natalie.setState(PlayerState.PLAYING);
        natalie.setColor("blue - natalie");
        List<Coordinate> traceNat = new ArrayList<>();
        natalie.setTrace(traceNat);

        Player olga = new Player();
        olga.setPlayerId(2351900);
        olga.setState(PlayerState.PLAYING);
        olga.setColor("yellow - olga");
        List<Coordinate> traceOlg = new ArrayList<>();
        olga.setTrace(traceOlg);

        Player rebecca = new Player();
        rebecca.setPlayerId(23452522);
        rebecca.setState(PlayerState.PLAYING);
        rebecca.setColor("green - rebecca");
        List<Coordinate> traceReb = new ArrayList<>();
        rebecca.setTrace(traceReb);

        List<Player> tronPlayers = new ArrayList<>();
        tronPlayers.add(mirjam);
        tronPlayers.add(natalie);
        tronPlayers.add(olga);
        tronPlayers.add(rebecca);

        Match match = new Match();
        match.setMatchId(12345);
        match.setState(MatchState.READY);
        match.setNumberOfPlayers(4);
        match.setMaxGridX(10);
        match.setMaxGridY(10);
        match.setPlayers(tronPlayers);

        GameLogic gl = new GameLogic();

        System.out.println("----------------tick---------------");
        for (Player p : match.getPlayers()) {
            System.out.println(p.getColor());
        }
        gl.processMatch(match); //

        for (Player p : match.getPlayers()) {
            System.out.println(p.getColor() + ": " + p.getHead());
        }
        match.setState(MatchState.RUNNING);
        System.out.println("MATCH IS NOW RUNNING");

        System.out.println("----------------tick---------------");
        gl.processMatch(match);
        for (Player p : match.getPlayers()) {
            System.out.println(p.getColor() + ": " + p.getHead());
        }

        System.out.println("----------------tick---------------");
        gl.processMatch(match);
        for (Player p : match.getPlayers()) {
            System.out.println(p.getColor() + ": " + p.getHead());
        }

        System.out.println("----------------tick---------------");
        gl.processMatch(match);
        for (Player p : match.getPlayers()) {
            System.out.println(p.getColor() + ": " + p.getHead());
        }

        System.out.println("----------------tick---------------");
        gl.processMatch(match);
        for (Player p : match.getPlayers()) {
            System.out.println(p.getColor() + ": " + p.getHead());
        }

        for (Player p : match.getPlayers()) {
            System.out.println(p.getColor() + ": " + p.getState());
        }

        System.out.println("----------------tick---------------");
        gl.processMatch(match);
        for (Player p : match.getPlayers()) {
            System.out.println(p.getColor() + ": " + p.getHead());
        }

        for (Player p : match.getPlayers()) {
            System.out.println(p.getColor() + ": " + p.getState());
        }
    }
}
