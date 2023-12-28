package haw.vs.middleware.clientStub.api;

public interface IClientStub {
    void invoke(String methodName, Object[] args, int modus);
    void invoke(String methodName, Object[] args, int modus, long stateId);
    void invokeSpecific(long id, String methodName, int modus);
}
