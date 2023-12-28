package haw.vs.middleware.clientStub.impl;

public class TCPSendThread implements Runnable{
    private SendQueue sendQueue;
    public TCPSendThread() {
        this.sendQueue = SendQueue.getInstance();
    }

    @Override
    public void run() {
        while (true) {
            // TODO
        }
    }
}
