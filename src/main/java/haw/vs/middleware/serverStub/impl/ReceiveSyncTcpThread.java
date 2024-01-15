package haw.vs.middleware.serverStub.impl;

import haw.vs.middleware.common.properties.MiddlewarePropertiesException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesHelper;
import haw.vs.middleware.serverStub.api.ICaller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ReceiveSyncTcpThread implements Runnable {
    private static final int TCP_SYNC_PORT;

    static {
        try {
            TCP_SYNC_PORT = MiddlewarePropertiesHelper.getSynchronousTcpPort();
        } catch (MiddlewarePropertiesException e) {
            throw new RuntimeException(e); //✅ no props no fun
        }
    }

    private ICaller caller;

    public ReceiveSyncTcpThread() {
        this.caller = Caller.getInstance();
    }

    @Override
    public void run() {
        try (ServerSocket welcomeSocket = new ServerSocket(TCP_SYNC_PORT)) {
            while (true) {
                Socket syncSocket = welcomeSocket.accept();

                Thread clientHandler = new Thread(() -> {

                    dealWithSync(syncSocket);

                });
                clientHandler.start();
            }

        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    private void dealWithSync(Socket syncSocket) {
        OutputStream outgoing = null;
        try {
            InputStream incoming = syncSocket.getInputStream();
            byte[] buffi = new byte[1024];
            int bytesRead;

            while ((bytesRead = incoming.read(buffi)) != -1) {
                caller.callSynchronously(Arrays.copyOf(buffi, bytesRead));
                break;
            }

            outgoing = syncSocket.getOutputStream();
            String ok = "ok";
            outgoing.write(ok.getBytes());
            outgoing.flush();
        } catch (IOException e) {
            String error = "error";
            assert outgoing != null;
            try {
                outgoing.write(error.getBytes());
                outgoing.flush();
            } catch (IOException ex) {
                System.err.println("Couldn't write to outputStream: " + ex.getMessage());
            }
            //throw new RuntimeException(e);
        } finally {
            try {
                syncSocket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

    }
}
