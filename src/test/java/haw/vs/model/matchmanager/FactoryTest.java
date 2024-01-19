package haw.vs.model.matchmanager;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.model.matchmanager.api.GameStateUpdaterFactory;
import haw.vs.model.matchmanager.api.IGameStateUpdater;
import haw.vs.model.matchmanager.api.IMatchController;
import haw.vs.model.matchmanager.api.MatchControllerFactory;
import haw.vs.model.matchmanager.impl.gamestateupdater.GameStateUpdater;
import haw.vs.model.matchmanager.impl.matchcontroller.MatchManager;
import haw.vs.model.matchmanager.impl.mock.MockGameStateUpdater;
import haw.vs.model.matchmanager.impl.mock.MockMatchController;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryTest {
    @Test
    public void testFactoryReturnsMockIfSetToTest() {
        PropertiesHelper.setPropertiesFile("model/matchmanager/factory_test1.properties");
        IGameStateUpdater gameStateUpdater = assertDoesNotThrow(() -> GameStateUpdaterFactory.getGameStateUpdater(ComponentType.MATCH_MANAGER));
        IMatchController matchController = assertDoesNotThrow(() -> MatchControllerFactory.getMatchController(ComponentType.MATCH_MANAGER));
        assertEquals(gameStateUpdater.getClass(), MockGameStateUpdater.class);
        assertEquals(matchController.getClass(), MockMatchController.class);
    }

    @Test
    public void testFactoryReturnsRealObjectIfSetToStandalone() {
        PropertiesHelper.setPropertiesFile("model/matchmanager/factory_test2.properties");
        IGameStateUpdater gameStateUpdater = assertDoesNotThrow(() -> GameStateUpdaterFactory.getGameStateUpdater(ComponentType.MATCH_MANAGER));
        IMatchController matchController = assertDoesNotThrow(() -> MatchControllerFactory.getMatchController(ComponentType.MATCH_MANAGER));
        assertEquals(gameStateUpdater.getClass(), GameStateUpdater.class);
        assertEquals(matchController.getClass(), MatchManager.class);
    }
}
