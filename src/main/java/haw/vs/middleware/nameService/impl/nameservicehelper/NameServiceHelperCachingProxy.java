package haw.vs.middleware.nameService.impl.nameservicehelper;

import haw.vs.middleware.nameService.api.INameServiceHelper;
import haw.vs.middleware.nameService.impl.nameservicehelper.NameServiceHelper;

import java.util.List;

public class NameServiceHelperCachingProxy implements INameServiceHelper {
    private final NameServiceHelper nameServiceHelper;

    public NameServiceHelperCachingProxy(NameServiceHelper nameServiceHelper) {
        this.nameServiceHelper = nameServiceHelper;
    }

    @Override
    public long bind(List<String> methodNames, int type) {
        return 0;
    }

    @Override
    public String lookup(String methodName) {
        return null;
    }

    @Override
    public String lookup(String methodName, long stateId) {
        return null;
    }

    @Override
    public String lookup(long specificId) {
        return null;
    }
}
