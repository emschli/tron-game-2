package haw.vs.model.matchmanager.tick;

import haw.vs.model.gamelogic.api.GameLogicFactory;
import haw.vs.model.matchmanager.state.Matches;

public class TickHandlerFactory {
    public static ITickHandler getTickHandler() {
        return new TickHandler(Matches.getInstance(), GameLogicFactory.getGameStateProcessor());
    }
}
