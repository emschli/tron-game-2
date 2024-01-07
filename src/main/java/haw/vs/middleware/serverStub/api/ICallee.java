package haw.vs.middleware.serverStub.api;

import haw.vs.middleware.nameService.impl.exception.NameServiceException;

public interface ICallee {
    void register() throws NameServiceException;
    void setId(long id);
}
