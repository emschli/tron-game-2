package haw.vs.middleware.serverStub.impl;

import haw.vs.middleware.common.properties.MiddlewarePropertiesException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesHelper;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ReceiveTcpThread implements Runnable {
    private ReceiveQueue receiveQueue;
    private static final int TCP_PORT;

    static {
        try {
            TCP_PORT = MiddlewarePropertiesHelper.getAsynchronousTcpPort();
        } catch (MiddlewarePropertiesException e) {
            throw new RuntimeException(e); //✅ no props no fun
        }
    }

    public ReceiveTcpThread() {
        this.receiveQueue = ReceiveQueue.getInstance();
    }

    @Override
    public void run() {
        try (ServerSocket welcomeSocket = new ServerSocket(TCP_PORT)) {
            while (true) {
                Socket clientSocket = welcomeSocket.accept();

                Thread clientHandler = new Thread(() -> dealWithClient(clientSocket));
                clientHandler.start();
            }
        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    private void dealWithClient(Socket clientSocket) {
        try {
            InputStream incoming = clientSocket.getInputStream();
            byte[] buffi = new byte[1024];
            int bytesRead;

            while ((bytesRead = incoming.read(buffi)) != -1) {
                receiveQueue.put(Arrays.copyOf(buffi, bytesRead));
            }

        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e); //🧵
        } finally {
            try{
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
