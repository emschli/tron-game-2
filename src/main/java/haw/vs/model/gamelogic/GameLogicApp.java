package haw.vs.model.gamelogic;

import haw.vs.common.ICallee;
import haw.vs.common.properties.AppType;
import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.model.gamelogic.api.GameStateProcessorFactory;

public class GameLogicApp {
    public void startApp(){
        try {
            if (PropertiesHelper.getAppType() == AppType.DISTRIBUTED) {
                ICallee gameStateProcessor = (ICallee) GameStateProcessorFactory.getGameStateProcessor(ComponentType.GAME_LOGIC);
                gameStateProcessor.register();
            }
        } catch (PropertiesException | NameServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
