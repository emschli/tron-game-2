package haw.vs.middleware.clientStub.impl;

import haw.vs.middleware.common.Pair;
import haw.vs.middleware.common.properties.MiddlewarePropertiesException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesHelper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSendThread implements Runnable{
    private SendQueue sendQueue;
    public UDPSendThread() {
        this.sendQueue = SendQueue.getSendQueue();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Pair<InetAddress, byte[]> pair = sendQueue.getUdpSendQueue().take();
                send(pair.getKey(), pair.getValue());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void send(InetAddress address, byte[] data) {
        try (DatagramSocket socket = new DatagramSocket()) {

            int serverport = MiddlewarePropertiesHelper.getAsynchronousUdpPort();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, serverport);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MiddlewarePropertiesException e) {
            throw new RuntimeException(e);
        }
    }
}

