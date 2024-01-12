package haw.vs.middleware.serverStub.api;

import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

import java.lang.reflect.Method;
import java.util.List;

public interface IServerStub {
    long register(List<Method> methods, Object callee, int type) throws NameServiceException, MethodNameAlreadyExistsException;
}
