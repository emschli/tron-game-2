package haw.vs.middleware.serverStub.impl;

import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.ICallee;
import haw.vs.middleware.serverStub.api.ICaller;
import haw.vs.middleware.serverStub.api.IServerStub;

import java.util.List;

public class ServerStubFacade implements IServerStub {
    private ICaller caller;

    public ServerStubFacade() {
        this.caller = Caller.getInstance();
    }

    @Override
    public void register(List<String> methodNames, ICallee callee, int type) throws NameServiceException {
        caller.register(methodNames, callee, type);
    }
}
