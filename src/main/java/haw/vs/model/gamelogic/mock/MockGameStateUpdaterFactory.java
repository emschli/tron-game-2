package haw.vs.model.gamelogic.mock;

import haw.vs.model.matchmanager.api.IGameStateUpdater;

public class MockGameStateUpdaterFactory {

    public IGameStateUpdater getGameStateUpdater() {
        return new MockGameStateUpdater();
    }

}
