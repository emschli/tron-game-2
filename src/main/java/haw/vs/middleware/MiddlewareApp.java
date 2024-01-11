package haw.vs.middleware;

import haw.vs.middleware.clientStub.impl.TCPSendThread;
import haw.vs.middleware.clientStub.impl.UDPSendThread;
import haw.vs.middleware.common.properties.MiddlewareAppType;
import haw.vs.middleware.common.properties.MiddlewarePropertiesException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesHelper;
import haw.vs.middleware.nameService.impl.NameServiceThread;
import haw.vs.middleware.serverStub.impl.Caller;
import haw.vs.middleware.serverStub.impl.ReceiveSyncTcpThread;
import haw.vs.middleware.serverStub.impl.ReceiveTcpThread;
import haw.vs.middleware.serverStub.impl.ReceiveUdpThread;

public class MiddlewareApp {
    public void startApp() {
        try {
            MiddlewareAppType appType = MiddlewarePropertiesHelper.getMiddlewareAppType();

            switch (appType) {
                case MIDDLEWARE:
                    //client stub
                    startMiddleware();
                    break;
                case NAME_SERVICE:
                    startNameService();
                    break;
                case BOTH:
                    startNameService();
                    startMiddleware();
            }
        } catch (MiddlewarePropertiesException e) {
            throw new RuntimeException(e);
        }
    }

    private void startMiddleware() throws MiddlewarePropertiesException {
        Thread tcpSendThread = new Thread(new TCPSendThread(), "SendTcpThread");
        Thread udpSendThread = new Thread(new UDPSendThread(), "SendUdpThread");
        tcpSendThread.start();
        udpSendThread.start();
        //server stub
        Thread receiveSyncTcpThread = new Thread(new ReceiveSyncTcpThread(), "ReceiveSyncTcpThread");
        Thread receiveTcpThread = new Thread(new ReceiveTcpThread(), "ReceiveTcpThread");
        Thread receiveUdpThread = new Thread(new ReceiveUdpThread(), "ReceiveUdpThread");
        Thread caller = new Thread(Caller.getInstance(), "CallerThread");
        receiveSyncTcpThread.start();
        receiveTcpThread.start();
        receiveUdpThread.start();
        caller.start();
    }

    private void startNameService() {
        Thread nameServiceThread = new Thread(new NameServiceThread(), "NameServiceThread");
        nameServiceThread.start();
    }
}
