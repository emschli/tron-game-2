package haw.vs.middleware.clientStub.impl;

import haw.vs.middleware.common.Pair;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesHelper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Sender {

    private SendQueue sendQueue;
    private Thread tcpThread;
    private Thread udpThread;

    public Sender() {
        sendQueue = SendQueue.getSendQueue();
        tcpThread = new Thread(new TCPSendThread());
        udpThread = new Thread(new UDPSendThread());

        tcpThread.start();
        udpThread.start();

    }

    public void sendSynchronouslyTcp(String sendTo, byte[] data) throws InvokeFailedException {
        int serverport = 0;
        try {
            serverport = MiddlewarePropertiesHelper.getSynchronousTcpPort();
        } catch (MiddlewarePropertiesException e) {
            throw new RuntimeException(e);
        }

        try (Socket socket = new Socket(InetAddress.getByName(sendTo), serverport)) {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            try {
                outputStream.writeInt(data.length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAsynchronouslyTcp(String sendTo, byte[] data) {
        try {
            sendQueue.getTcpSendQueue().put(new Pair<>(InetAddress.getByName(sendTo), data));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendAsynchronouslyUdp(String sendTo, byte[] data) {
        try {
            sendQueue.getUdpSendQueue().put(new Pair<>(InetAddress.getByName(sendTo), data));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
