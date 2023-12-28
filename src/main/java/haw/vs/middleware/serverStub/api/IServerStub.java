package haw.vs.middleware.serverStub.api;

public interface IServerStub {
    void register(String methodName, ICallee callee, int type);
}
