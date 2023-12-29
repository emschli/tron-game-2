package haw.vs.model.gamelogic.mock;

import haw.vs.model.matchmanager.api.IGameStateUpdater;

public class MockGameStateUpdaterFactory {

    public static IGameStateUpdater getGameStateUpdater() {
        return new MockGameStateUpdater();
    }

}
