package haw.vs.middleware.clientStub.api;

import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

public interface IClientStub {
    void invoke(String methodName, Object[] args, int modus) throws NameServiceException, InvokeFailedException;
    void invoke(String methodName, Object[] args, int modus, long stateId) throws NameServiceException, InvokeFailedException;
    void invokeSpecific(long id, String methodName, Object[] args, int modus) throws InvokeFailedException;
}
