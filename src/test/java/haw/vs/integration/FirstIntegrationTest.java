package haw.vs.integration;

import haw.vs.common.PlayerConfigData;
import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.api.IInput;
import haw.vs.controller.api.InputFactory;
import haw.vs.model.matchmanager.MatchManagerApp;

import java.util.concurrent.CountDownLatch;

public class FirstIntegrationTest {
    public static void main(String[] args) throws PropertiesException {
        PropertiesHelper.setPropertiesFile("integration/first_integration_test.properties");
        MatchManagerApp app = new MatchManagerApp();
        app.startApp(new CountDownLatch(0), new CountDownLatch(1));

        IInput input = InputFactory.getInput(ComponentType.CONTROLLER);
        PlayerConfigData configData = new PlayerConfigData(500, 600);

        //Start Game 2 players
        input.joinGameController(1L, 3, configData);
        input.joinGameController(2L, 3, configData);
        input.cancelWaitController(1L, 1L, 3);

        System.out.println("");


    }
}
