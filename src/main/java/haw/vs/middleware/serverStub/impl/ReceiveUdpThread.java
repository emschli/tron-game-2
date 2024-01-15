package haw.vs.middleware.serverStub.impl;

import haw.vs.middleware.common.properties.MiddlewarePropertiesException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesHelper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveUdpThread implements Runnable {

    private ReceiveQueue receiveQueue;
    private static final int UDP_PORT;

    static {
        try {
            UDP_PORT = MiddlewarePropertiesHelper.getAsynchronousUdpPort();
        } catch (MiddlewarePropertiesException e) {
            throw new RuntimeException(e); //✅ no props no fun
        }
    }

    public ReceiveUdpThread() {
        this.receiveQueue = ReceiveQueue.getInstance();
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(UDP_PORT)) {
            byte[] receiveData = new byte[32_000];

            while (true) {
                DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(packet);
                receiveQueue.put(packet.getData());
            }

        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.err.println("Sth went wrong receiving UDP: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e); //🧵
        }
    }
}
