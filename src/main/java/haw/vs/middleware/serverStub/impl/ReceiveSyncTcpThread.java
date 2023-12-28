package haw.vs.middleware.serverStub.impl;

public class ReceiveSyncTcpThread implements Runnable{
    private ReceiveQueue receiveQueue;
    public ReceiveSyncTcpThread() {
        this.receiveQueue = ReceiveQueue.getInstance();
    }

    @Override
    public void run() {
        // TODO impl for receiving sync TCP
    }
}
