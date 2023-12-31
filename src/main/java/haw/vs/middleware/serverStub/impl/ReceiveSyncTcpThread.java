package haw.vs.middleware.serverStub.impl;

import haw.vs.middleware.serverStub.api.ICaller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ReceiveSyncTcpThread implements Runnable {
    private static final int TCP_SYNC_PORT = 60125;
    private ICaller caller;

    public ReceiveSyncTcpThread() {
        this.caller = Caller.getInstance();
    }

    @Override
    public void run() {
        try (ServerSocket welcomeSocket = new ServerSocket(TCP_SYNC_PORT)) {
            while (true) {
                Socket syncSocket = welcomeSocket.accept();

                Thread clientHandler = new Thread(() -> dealWithSync(syncSocket));
                clientHandler.start();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void dealWithSync(Socket syncSocket) {
        try {
            InputStream incoming = syncSocket.getInputStream();
            byte[] buffi = new byte[1024];
            int bytesRead;

            while ((bytesRead = incoming.read(buffi)) != -1) {
                caller.callSynchronously(Arrays.copyOf(buffi, bytesRead));
            }

            OutputStream outgoing = syncSocket.getOutputStream();
            String ok = "OK";
            outgoing.write(ok.getBytes());
            // TODO how/where if error?
            outgoing.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                syncSocket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
