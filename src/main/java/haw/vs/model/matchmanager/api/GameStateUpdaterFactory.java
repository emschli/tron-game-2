package haw.vs.model.matchmanager.api;

import haw.vs.model.matchmanager.mock.MockGameStateUpdater;

public class GameStateUpdaterFactory {
    public static IGameStateUpdater getGameStateUpdater() {
        return new MockGameStateUpdater();
    }
}
