package haw.vs.middleware.serverStub.api;

public interface ICaller {
    void register(String methodName, ICallee callee, int type);

    void callSynchronously(byte[] data);
}
