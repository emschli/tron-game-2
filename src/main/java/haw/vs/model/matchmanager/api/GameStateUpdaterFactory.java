package haw.vs.model.matchmanager.api;

import haw.vs.model.matchmanager.GameStateUpdater;
import haw.vs.model.matchmanager.mock.MockGameStateUpdater;
import haw.vs.model.matchmanager.state.Matches;

public class GameStateUpdaterFactory {
    public static IGameStateUpdater getGameStateUpdater() {
//        return new MockGameStateUpdater();

        //TODO: make dependent on Properties-File
        return new GameStateUpdater(Matches.getInstance());
    }
}
