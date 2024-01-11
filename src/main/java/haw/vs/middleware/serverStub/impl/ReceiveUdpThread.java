package haw.vs.middleware.serverStub.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveUdpThread implements Runnable {

    private ReceiveQueue receiveQueue;
    private static final int UDP_PORT = 60123;
    public ReceiveUdpThread() {
        this.receiveQueue = ReceiveQueue.getInstance();
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(UDP_PORT)){
            byte[] receiveData = new byte[1024];

            while (true){
                DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(packet);
                receiveQueue.put(packet.getData());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
