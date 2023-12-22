package haw.vs.model.matchmanager;

import haw.vs.common.PlayerConfigData;
import haw.vs.model.matchmanager.api.GameStateUpdaterFactory;
import haw.vs.model.matchmanager.api.IGameStateUpdater;
import haw.vs.model.matchmanager.api.IMatchController;
import haw.vs.model.matchmanager.api.MatchControllerFactory;

public class MatchManagerTest {
    public static void main(String[] args) {
        MatchManagerApp app = new MatchManagerApp();
        app.startApp();

        IMatchController matchController = MatchControllerFactory.getMatchController();
        IGameStateUpdater gameStateUpdater = GameStateUpdaterFactory.getGameStateUpdater();

        //Add Player To Game
        PlayerConfigData configData = new PlayerConfigData(10, 10);
        matchController.addPlayerToMatch(1, 2, configData);

    }
}
