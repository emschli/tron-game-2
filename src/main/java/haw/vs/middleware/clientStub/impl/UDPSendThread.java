package haw.vs.middleware.clientStub.impl;

public class UDPSendThread implements Runnable{
    private SendQueue sendQueue;
    public UDPSendThread() {
        this.sendQueue = SendQueue.getInstance();
    }

    @Override
    public void run() {
        while (true) {
            // TODO
        }
    }
}

