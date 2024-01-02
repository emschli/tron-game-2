package haw.vs.middleware.nameService.impl.nameservicehelper;

import haw.vs.middleware.nameService.api.INameServiceHelper;

import java.util.List;

public class NameServiceHelper implements INameServiceHelper {
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
    public String lookupSpecific(String methodName, long specificId) {
        return null;
    }
}
