package haw.vs.common;

import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

public interface ICallee {
    void register() throws NameServiceException, MethodNameAlreadyExistsException;
}
