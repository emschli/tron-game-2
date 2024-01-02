package haw.vs.middleware.nameService.impl;

import haw.vs.middleware.nameService.api.INameService;

import java.util.List;

public class NameServiceCachingProxy implements INameService {
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
