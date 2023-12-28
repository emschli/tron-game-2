package haw.vs.middleware.serverStub.api;

public interface ICallee {
    void call(String methodName, String args);
    void setId(long id);
}
