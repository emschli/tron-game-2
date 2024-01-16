package haw.vs.middleware.clientStub.impl;

import haw.vs.middleware.common.Pair;
import haw.vs.middleware.common.properties.MiddlewarePropertiesException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesHelper;

import java.net.InetAddress;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPSendThread implements Runnable{
    private SendQueue sendQueue;
    public TCPSendThread() {
        this.sendQueue = SendQueue.getSendQueue();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Pair<InetAddress, byte[]> pair = sendQueue.takeTcpQueue();
                send(pair.getKey(), pair.getValue());
            } catch (InterruptedException | MiddlewarePropertiesException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void send(InetAddress address, byte[] data) throws MiddlewarePropertiesException {


        int serverport = MiddlewarePropertiesHelper.getAsynchronousTcpPort();

        try (Socket socket = new Socket(address, serverport)) {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
