package haw.vs.middleware.serverStub.impl;

public class ReceiveUdpThread implements Runnable {

    private ReceiveQueue receiveQueue;
    public ReceiveUdpThread() {
        this.receiveQueue = ReceiveQueue.getInstance();
    }

    @Override
    public void run() {
        // TODO impl for receiving UDP
    }
}
