package haw.vs.middleware.clientStub.impl;

import haw.vs.middleware.common.Pair;

import java.net.InetAddress;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SendQueue {
    private static SendQueue sendQueue;

    private LinkedList<Pair<InetAddress, byte[]>> tcpSendQueue;
    private LinkedList<Pair<InetAddress, byte[]>> udpSendQueue;

    private Lock tcpLock;
    private Condition tcpTake;

    private Lock udpLock;
    private Condition udpTake;


    private SendQueue() {
        tcpSendQueue = new LinkedList<>();
        udpSendQueue = new LinkedList<>();

        tcpLock = new ReentrantLock();
        tcpTake = tcpLock.newCondition();

        udpLock = new ReentrantLock();
        udpTake = udpLock.newCondition();
    }

    public static synchronized SendQueue getSendQueue() {
        if (sendQueue == null) {
            sendQueue = new SendQueue();
        }
        return sendQueue;
    }

    public void putTcpQueue(Pair<InetAddress, byte[]> pair) {
        tcpLock.lock();
        tcpSendQueue.add(pair);
        tcpTake.signalAll();
        tcpLock.unlock();
    }

    public Pair<InetAddress, byte[]> takeTcpQueue() throws InterruptedException {
        Pair<InetAddress, byte[]> pair;

        tcpLock.lock();

        while (tcpSendQueue.isEmpty()) {
            tcpTake.await();
        }
        pair = tcpSendQueue.removeFirst();

        tcpLock.unlock();

        return pair;
    }

    public void putUdpQueue(Pair<InetAddress, byte[]> pair) {
        udpLock.lock();
        udpSendQueue.add(pair);
        udpTake.signalAll();
        udpLock.unlock();
    }

    public Pair<InetAddress, byte[]> takeUdpQueue() throws InterruptedException {
        Pair<InetAddress, byte[]> pair;

        udpLock.lock();
        while (udpSendQueue.isEmpty()) {
            udpTake.await();
        }

        pair = udpSendQueue.removeFirst();
        udpLock.unlock();

        return pair;
    }
}
