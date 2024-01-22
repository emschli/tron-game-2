package haw.vs.model.gamelogic.impl.mock;

import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.api.IGameStateProcessor;

public class MockGameStateProcessor implements IGameStateProcessor {
    @Override
    public void addTask(Match match) {
        System.out.println("GameStateProcessor received: " + match);
    }
}
