package haw.vs.middleware.serverStub.impl;

import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.ICallee;
import haw.vs.middleware.serverStub.api.ICaller;
import haw.vs.middleware.serverStub.api.IServerStub;

import java.lang.reflect.Method;
import java.util.List;

public class ServerStubFacade implements IServerStub {
    private ICaller caller;

    public ServerStubFacade() {
        this.caller = Caller.getInstance();
    }

    @Override
    public void register(List<Method> methods, ICallee callee, int type) throws NameServiceException {
        caller.register(methods, callee, type);
    }
}
