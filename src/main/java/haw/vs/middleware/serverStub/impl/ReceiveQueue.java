package haw.vs.middleware.serverStub.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ReceiveQueue {
    private static ReceiveQueue receiveQueue;
    private BlockingQueue<byte[]> commonReceiveQueue;


    private ReceiveQueue() {
        commonReceiveQueue = new LinkedBlockingQueue<>();
    }

    public static synchronized ReceiveQueue getInstance() {
        if (receiveQueue == null) {
            receiveQueue = new ReceiveQueue();
        }
        return receiveQueue;
    }

    public void put(byte[] data) throws InterruptedException {
            commonReceiveQueue.put(data);
    }

    public byte[] take() throws InterruptedException {
        return commonReceiveQueue.take();
    }
}
