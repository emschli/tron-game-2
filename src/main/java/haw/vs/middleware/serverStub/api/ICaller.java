package haw.vs.middleware.serverStub.api;

import haw.vs.middleware.nameService.impl.exception.NameServiceException;

import java.lang.reflect.Method;
import java.util.List;

public interface ICaller {
    void register(List<Method> methods, ICallee callee, int type) throws NameServiceException;

    void callSynchronously(byte[] data);
}
