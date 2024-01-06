package haw.vs.middleware.clientStub.impl;

import haw.vs.middleware.common.Pair;

import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SendQueue {
    private static SendQueue sendQueue;
    private BlockingQueue<Pair<InetAddress, byte[]>> tcpSendQueue;
    private BlockingQueue<Pair<InetAddress, byte[]>> udpSendQueue;
    private Lock lock;


    private SendQueue() {
        tcpSendQueue = new LinkedBlockingQueue<>();
        udpSendQueue = new LinkedBlockingQueue<>();
        lock = new ReentrantLock();
    }

    public static synchronized SendQueue getSendQueue() {
        if (sendQueue == null) {
            sendQueue = new SendQueue();
        }
        return sendQueue;
    }


    public BlockingQueue<Pair<InetAddress, byte[]>> getTcpSendQueue() {
        return tcpSendQueue;
    }

    public BlockingQueue<Pair<InetAddress, byte[]>> getUdpSendQueue() {
        return udpSendQueue;
    }
}
