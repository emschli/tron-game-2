package haw.vs.middleware.nameService.api;

import haw.vs.middleware.common.properties.MiddlewarePropertiesException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesHelper;
import haw.vs.middleware.nameService.impl.nameservicehelper.NameServiceHelper;
import haw.vs.middleware.nameService.impl.nameservicehelper.NameServiceHelperCachingProxy;

public class NameServiceHelperFactory {
    public static synchronized INameServiceHelper getNameServiceHelper() {
        String nameServiceIp;
        int nameServicePort;
        try {
            nameServiceIp = MiddlewarePropertiesHelper.getNameServiceHost();
            nameServicePort = MiddlewarePropertiesHelper.getNameServicePort();
        } catch (MiddlewarePropertiesException e) {
            throw new RuntimeException(e); //✅ no props no fun
        }

        return new NameServiceHelperCachingProxy(new NameServiceHelper(nameServiceIp, nameServicePort));
    }
}
