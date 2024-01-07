package haw.vs.middleware.clientStub.api;

import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.middleware.clientStub.impl.Marshaller;
import haw.vs.middleware.clientStub.mock.MarschallerMock;
import haw.vs.middleware.nameService.api.NameServiceHelperFactory;
import haw.vs.middleware.nameService.impl.NameServiceFactory;

public class ClientStubFactory {

    public static IClientStub getClientStub() throws PropertiesException {


        switch (PropertiesHelper.getAppType()) {
            case DISTRIBUTED:
                return new Marshaller(NameServiceHelperFactory.getNameServiceHelper());
            default:
                return new MarschallerMock();
        }

    }
}
