package haw.vs.middleware.nameService.impl;

import haw.vs.middleware.nameService.impl.exception.NameServiceBindException;

import java.util.List;

public interface INameService {
    long bind(List<String> methodNames, int type, String ip) throws NameServiceBindException;
    String lookup(String methodName) throws NameServiceLookupException;
    String lookup(String methodName, long stateId) throws NameServiceLookupException;
    String lookupSpecific(String methodName, long specificId) throws NameServiceLookupException;
}
