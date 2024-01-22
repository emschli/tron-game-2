package haw.vs.model.matchmanager.impl.tick;

import haw.vs.common.properties.ComponentType;
import haw.vs.model.gamelogic.api.GameStateProcessorFactory;
import haw.vs.model.matchmanager.impl.state.Matches;

public class TickHandlerFactory {
    public static ITickHandler getTickHandler() {
        return new TickHandler(Matches.getInstance(), GameStateProcessorFactory.getGameStateProcessor(ComponentType.MATCH_MANAGER));
    }
}
