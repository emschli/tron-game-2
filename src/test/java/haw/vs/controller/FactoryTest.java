package haw.vs.controller;

import static org.junit.jupiter.api.Assertions.*;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.api.GameViewUpdateFactory;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.controller.api.IInput;
import haw.vs.controller.api.InputFactory;
import haw.vs.controller.impl.gameviewupdate.GameViewUpdate;
import haw.vs.controller.impl.input.InputHandler;
import haw.vs.controller.impl.mock.MockGameViewUpdate;
import haw.vs.controller.impl.mock.MockInput;
import org.junit.jupiter.api.Test;

public class FactoryTest {

    @Test
    public void testFactoryReturnsMockIfSetToTest() {
        PropertiesHelper.setPropertiesFile("controller/factory_test1.properties");

        // Überprüfe, ob die GameViewUpdateFactory die MockGameViewUpdate zurückgibt
        IGameViewUpdate gameViewUpdate = assertDoesNotThrow(()-> GameViewUpdateFactory.getGameViewUpdate(ComponentType.CONTROLLER));
        assertEquals(gameViewUpdate.getClass(), MockGameViewUpdate.class);

        // Überprüfe, ob die InputFactory die MockInput zurückgibt
        IInput input = assertDoesNotThrow(()->InputFactory.getInput(ComponentType.CONTROLLER));
        assertEquals(input.getClass(), MockInput.class);
    }

    @Test
    public void testFactoryReturnsRealObjectIfSetToStandalone() {
        PropertiesHelper.setPropertiesFile("controller/factory_test2.properties");

        // Überprüfe, ob die GameViewUpdateFactory ein echtes GameViewUpdate-Objekt zurückgibt
        IGameViewUpdate gameViewUpdate = assertDoesNotThrow(()->GameViewUpdateFactory.getGameViewUpdate(ComponentType.CONTROLLER));
        assertEquals(gameViewUpdate.getClass(), GameViewUpdate.class);

        // Überprüfe, ob die InputFactory ein echtes Input-Objekt zurückgibt
        IInput input = assertDoesNotThrow(()->InputFactory.getInput(ComponentType.CONTROLLER));
        assertEquals(input.getClass(), InputHandler.class);
    }
}