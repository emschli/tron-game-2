package haw.vs.middleware.nameService.api;

import haw.vs.middleware.nameService.api.INameServiceHelper;
import haw.vs.middleware.nameService.impl.nameservicehelper.NameServiceHelper;
import haw.vs.middleware.nameService.impl.nameservicehelper.NameServiceHelperCachingProxy;

public class NameServiceHelperFactory {
    public static INameServiceHelper getNameServiceHelper() {
        return new NameServiceHelperCachingProxy(new NameServiceHelper());
    }
}
