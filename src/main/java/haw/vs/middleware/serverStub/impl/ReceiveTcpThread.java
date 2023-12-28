package haw.vs.middleware.serverStub.impl;

public class ReceiveTcpThread implements Runnable{
    private ReceiveQueue receiveQueue;
    public ReceiveTcpThread() {
        this.receiveQueue = ReceiveQueue.getInstance();
    }

    @Override
    public void run() {
        // TODO impl for receiving TCP
    }
}
