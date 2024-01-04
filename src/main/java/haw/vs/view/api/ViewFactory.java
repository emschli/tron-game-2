package haw.vs.view.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.view.mock.MockPlayerInputHandler;
import haw.vs.view.mock.MockViewFacade;

public class ViewFactory {
    public static IViewFacade getView() throws PropertiesException {


        if (PropertiesHelper.isTest(ComponentType.VIEW)) {
            return new MockViewFacade();
        }

        switch (PropertiesHelper.getAppType()) {
            case STANDALONE -> {
                //return new ViewFacade(); TODO
                return null;
            } default -> {
                return new MockViewFacade();
            }
        }
    }
}
