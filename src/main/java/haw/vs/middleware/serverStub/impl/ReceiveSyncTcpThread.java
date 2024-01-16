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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReceiveSyncTcpThread implements Runnable {
    private static final int TCP_SYNC_PORT;
    private static final int NO_OF_RECEIVE_SYNC_THREADS = 3;

    static {
        try {
            TCP_SYNC_PORT = MiddlewarePropertiesHelper.getSynchronousTcpPort();
        } catch (MiddlewarePropertiesException e) {
            throw new RuntimeException(e);
        }
    }

    private ICaller caller;
    private final ExecutorService executorService;

    public ReceiveSyncTcpThread() {
        this.executorService = Executors.newFixedThreadPool(NO_OF_RECEIVE_SYNC_THREADS);
        this.caller = Caller.getInstance();
    }

    @Override
    public void run() {
        try (ServerSocket welcomeSocket = new ServerSocket(TCP_SYNC_PORT)) {
            while (true) {
                Socket syncSocket = welcomeSocket.accept();

                executorService.submit(()-> {
                    try {
                        dealWithSync(syncSocket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void dealWithSync(Socket syncSocket) throws IOException {
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
            outgoing.write(error.getBytes());
            outgoing.flush();
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
