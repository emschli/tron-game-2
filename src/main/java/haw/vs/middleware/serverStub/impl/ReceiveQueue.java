package haw.vs.middleware.serverStub.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReceiveQueue {
    private static ReceiveQueue receiveQueue;
    private BlockingQueue<byte[]> commonReceiveQueue;
    private Lock lock;


    private ReceiveQueue() {
        commonReceiveQueue = new LinkedBlockingQueue<>();
        lock = new ReentrantLock();
    }

    public static synchronized ReceiveQueue getInstance() {
        if (receiveQueue == null) {
            receiveQueue = new ReceiveQueue();
        }
        return receiveQueue;
    }

    public void put(byte[] data) {
        lock.lock();
        try {
            commonReceiveQueue.put(data);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public byte[] take() {
        lock.lock();
        try {
            return commonReceiveQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }
}
