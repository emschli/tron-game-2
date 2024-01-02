package haw.vs.middleware.nameService.api;

import java.util.List;

public interface INameService {
    long bind(List<String> methodNames, int type);
    String lookup(String methodName);
    String lookup(String methodName, long stateId);
    String lookup(long specificId);
}
