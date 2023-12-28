package haw.vs.middleware.clientStub.impl;

import haw.vs.middleware.clientStub.api.IClientStub;

public class ClientStubFactory {
    private static IClientStub clientStub;

    public ClientStubFactory(IClientStub clientStub) {
        this.clientStub = clientStub;
    }

    public static IClientStub getClientStub() {
        return clientStub;
    }
}
