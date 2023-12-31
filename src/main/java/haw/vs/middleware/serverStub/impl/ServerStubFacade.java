package haw.vs.middleware.serverStub.impl;

import haw.vs.middleware.serverStub.api.ICallee;
import haw.vs.middleware.serverStub.api.ICaller;
import haw.vs.middleware.serverStub.api.IServerStub;

public class ServerStubFacade implements IServerStub {
    private ICaller caller;

    public ServerStubFacade() {
        this.caller = Caller.getInstance();
    }

    @Override
    public void register(String methodName, ICallee callee, int type) {
        caller.register(methodName, callee, type);
    }
}
