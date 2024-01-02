package haw.vs.middleware.nameService.api;

import java.util.List;

public interface INameServiceHelper {
    long bind(List<String> methodNames, int type);
    String lookup(String methodName);
    String lookup(String methodName, long stateId);
    String lookupSpecific(String methodName, long specificId);
}
