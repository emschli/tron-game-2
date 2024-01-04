package haw.vs.middleware.serverStub.api;

import java.util.List;

public interface IServerStub {
    //void register(String methodName, ICallee callee, int type);

    void register(List<String> methodNames, ICallee callee, int type);
}
