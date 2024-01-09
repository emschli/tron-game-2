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
    public void startMiddleware() {
        try {
            MiddlewareAppType appType = MiddlewarePropertiesHelper.getMiddlewareAppType();

            switch (appType) {
                case MIDDLEWARE:
                    //client stub
                    Thread tcpSendThread = new Thread(new TCPSendThread());
                    Thread udpSendThread = new Thread(new UDPSendThread());
                    tcpSendThread.start();
                    udpSendThread.start();
                    //server stub
                    Thread receiveSyncTcpThread = new Thread(new ReceiveSyncTcpThread());
                    Thread receiveTcpThread = new Thread(new ReceiveTcpThread());
                    Thread receiveUdpThread = new Thread(new ReceiveUdpThread());
                    Thread caller = new Thread(Caller.getInstance());
                    receiveSyncTcpThread.start();
                    receiveTcpThread.start();
                    receiveUdpThread.start();
                    caller.start();
                    break;
                case NAME_SERVICE:
                    Thread nameServiceThread = new Thread(new NameServiceThread());
                    nameServiceThread.start();
            }
        } catch (MiddlewarePropertiesException e) {
            throw new RuntimeException(e);
        }
    }
}
