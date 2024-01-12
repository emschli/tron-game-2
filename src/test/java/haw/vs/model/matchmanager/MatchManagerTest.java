package haw.vs.model.matchmanager;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.model.common.Match;
import haw.vs.model.common.MatchState;
import haw.vs.model.common.Player;
import haw.vs.model.common.PlayerState;
import haw.vs.model.matchmanager.api.GameStateUpdaterFactory;
import haw.vs.model.matchmanager.api.IGameStateUpdater;
import haw.vs.model.matchmanager.api.IMatchController;
import haw.vs.model.matchmanager.api.MatchControllerFactory;
import haw.vs.model.matchmanager.state.Colors;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class MatchManagerTest {
    public static void main(String[] args) throws InterruptedException, PropertiesException {
        PropertiesHelper.setPropertiesFile("model/matchmanager/match_manager_test.properties");
        MatchManagerApp app = new MatchManagerApp();
        app.startApp(new CountDownLatch(0), new CountDownLatch(1));

        IMatchController matchController = MatchControllerFactory.getMatchController(ComponentType.MATCH_MANAGER);
        IGameStateUpdater gameStateUpdater = GameStateUpdaterFactory.getGameStateUpdater(ComponentType.MATCH_MANAGER);

        PlayerConfigData configData = new PlayerConfigData(10, 10);

        //Add Player To Game
        matchController.addPlayerToMatchMatchManager(1L, 3, configData);
        //Remove Only Player from Game
        matchController.deletePlayerFromMatchMatchManager(1L, 1L, 3);

        //Remove One Player from Game
        matchController.addPlayerToMatchMatchManager(2L, 3, configData);
        matchController.addPlayerToMatchMatchManager(1L, 3, configData);
        matchController.deletePlayerFromMatchMatchManager(1L, 1L, 3);

        // Make the Game Full
        matchController.addPlayerToMatchMatchManager(1L, 3, configData);
        matchController.addPlayerToMatchMatchManager(3L, 3, configData);

        Match match = getInitialMatch();
        Thread.sleep(2000);

        // Game should start
        gameStateUpdater.updateMatchManager(match.copy());

        Thread.sleep(2000);
        match.setState(MatchState.RUNNING);
        for (Player player : match.getPlayers()) {
            player.setState(PlayerState.PLAYING);
        }

        //Player can make Input
        matchController.movePlayerMatchManager(1L, 2L, Direction.DOWN);

        //One Player Dies
        match.getPlayers().get(0).setState(PlayerState.DEAD);
        gameStateUpdater.updateMatchManager(match.copy());

        Thread.sleep(2000);

        //Game Ends
        match.setState(MatchState.ENDED);
        match.removePlayer(1);
        match.getPlayers().get(0).setState(PlayerState.DEAD);
        match.getPlayers().get(1).setState(PlayerState.WON);

        gameStateUpdater.updateMatchManager(match.copy());
    }

    private static Match getInitialMatch() {
        Match match = new Match();
        PlayerConfigData configData = new PlayerConfigData(10, 10);
        match.setMatchId(2);
        match.setState(MatchState.READY);
        match.setNumberOfPlayers(3);
        match.setPlayers(new ArrayList<>());
        Player player1 = new Player();
        player1.setPlayerId(1);
        player1.setState(PlayerState.WAITING);
        player1.setConfigData(configData);
        match.addPlayer(player1);
        Player player2 = new Player();
        player2.setPlayerId(2);
        player2.setState(PlayerState.WAITING);
        player2.setConfigData(configData);
        match.addPlayer(player2);
        Player player3 = new Player();
        player3.setPlayerId(3);
        player3.setState(PlayerState.WAITING);
        player3.setConfigData(configData);
        match.addPlayer(player3);
        applyColors(match);
        return match;
    }

    private static void applyColors(Match match) {
        for (int i = 0; i < match.getPlayers().size(); i++) {
            Player player = match.getPlayers().get(i);
            player.setColor(Colors.getColor(i + 1));
        }
    }
}
