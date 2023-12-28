package haw.vs.middleware.clientStub.impl;

public class Sender {

    private SendQueue sendQueue;

    public Sender() {
        sendQueue = SendQueue.getInstance();

    }

    public void sendSynchronouslyTcp(String sendTo, byte[] data) {
        //TODO
    }

    public void sendAsynchronouslyTcp(String sendTo, byte[] data) {
        //TODO
    }

    public void sendAsynchronouslyUdp(String sendTo, byte[] data) {
        //TODO
    }
}
