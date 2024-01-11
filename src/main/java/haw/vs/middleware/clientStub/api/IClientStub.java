package haw.vs.middleware.clientStub.api;

import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

public interface IClientStub {
    void invoke(String methodName, int modus, Object... args) throws NameServiceException, InvokeFailedException;
    void invoke(String methodName, int modus, long stateId, Object... args) throws NameServiceException, InvokeFailedException;
    void invokeSpecific(long id, String methodName, int modus, Object... args) throws InvokeFailedException;
}
