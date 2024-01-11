package haw.vs.integration;

import haw.vs.common.PlayerConfigData;
import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.controller.api.IInput;
import haw.vs.controller.api.InputFactory;
import haw.vs.model.matchmanager.MatchManagerApp;

public class FirstIntegrationTest {
    public static void main(String[] args) throws PropertiesException {
        PropertiesHelper.setPropertiesFile("integration/first_integration_test.properties");
        MatchManagerApp app = new MatchManagerApp();
        app.startApp();

        IInput input = InputFactory.getInput(ComponentType.CONTROLLER);
        PlayerConfigData configData = new PlayerConfigData(500, 600);

        //Start Game 2 players
        input.joinGame(1, 3, configData);
        input.joinGame(2, 3, configData);
        input.cancelWait(1, 1, 3);

        System.out.println("");


    }
}
