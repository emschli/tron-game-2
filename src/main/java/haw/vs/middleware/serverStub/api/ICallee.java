package haw.vs.middleware.serverStub.api;

public interface ICallee {
    void call(String methodName, Object[] args);
    void setId(long id);
}
