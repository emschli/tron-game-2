package haw.vs.middleware.serverStub.api;

import haw.vs.middleware.nameService.impl.exception.NameServiceException;

import java.util.List;

public interface ICaller {
    //void register(String methodName, ICallee callee, int type);

    void register(List<String> methodNames, ICallee callee, int type) throws NameServiceException;

    void callSynchronously(byte[] data);
}
