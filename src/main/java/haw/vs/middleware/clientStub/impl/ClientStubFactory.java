package haw.vs.middleware.clientStub.impl;

import haw.vs.middleware.clientStub.api.IClientStub;

public class ClientStubFactory {
    private IClientStub clientStub;

    public ClientStubFactory(IClientStub clientStub) {
        this.clientStub = clientStub;
    }

    public IClientStub getClientStub() {
        return clientStub;
    }
}
