package haw.vs.middleware.nameService.api;

import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import java.util.List;

public interface INameServiceHelper {
    long bind(List<String> methodNames, int type) throws NameServiceException;
    String lookup(String methodName) throws NameServiceException;
    String lookup(String methodName, long stateId) throws NameServiceException;
    String lookupSpecific(String methodName, long specificId) throws NameServiceException;
}
