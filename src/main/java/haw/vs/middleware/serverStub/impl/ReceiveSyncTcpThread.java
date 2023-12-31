package haw.vs.middleware.serverStub.impl;

import haw.vs.middleware.serverStub.api.ICaller;

public class ReceiveSyncTcpThread implements Runnable{
    private ICaller caller;
    public ReceiveSyncTcpThread() {
        this.caller = Caller.getInstance();
    }

    @Override
    public void run() {
        // TODO impl for receiving sync TCP
    }
}
