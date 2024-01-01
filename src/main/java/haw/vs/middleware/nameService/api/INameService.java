package haw.vs.middleware.nameService.api;

import java.util.List;

public interface INameService {
    long bind(List<String> methodNames);
    String lookup(String methodName);
    String lookup(String methodName, long stateId);
    String lookup(long specificId);
    void bindState(long nodeId, long stateId);
    void clearState(long stateId);
}
