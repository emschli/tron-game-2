package haw.vs.model.matchmanager.tick;

import haw.vs.common.properties.PropertiesException;
import haw.vs.model.gamelogic.api.GameStateProcessorFactory;
import haw.vs.model.matchmanager.state.Matches;

public class TickHandlerFactory {
    public static ITickHandler getTickHandler() throws PropertiesException {
        return new TickHandler(Matches.getInstance(), GameStateProcessorFactory.getGameStateProcessor());
    }
}
