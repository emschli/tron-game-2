package haw.vs.middleware.nameService.impl;

import haw.vs.middleware.nameService.impl.exception.NameServiceBindException;
import haw.vs.middleware.nameService.impl.loadbalancer.ILoadBalancer;

import java.util.List;

import static haw.vs.middleware.nameService.impl.NameServiceData.*;

public class NameService implements INameService {
    private final NameServiceData nameServiceData;
    private final ILoadBalancer loadBalancer;

    public NameService(NameServiceData nameServiceData, ILoadBalancer loadBalancer) {
        this.nameServiceData = nameServiceData;
        this.loadBalancer = loadBalancer;
    }

    /**
     * Registers the Methods and returns specific or bundle id
     * @param methodNames
     * @param type
     * @param ip
     * @return specificId (if type=2) or bundleId (if type=0 or type=1)
     */
    @Override
    public long bind(List<String> methodNames, int type, String ip) throws NameServiceBindException {
        long id;
        if (type == STATELESS) {
            id = nameServiceData.addStatelessMethods(methodNames, ip);
        } else if (type == STATEFUL) {
            id = nameServiceData.addStatefulMethods(methodNames, ip);
        } else if (type == SPECIFIC) {
            id = nameServiceData.addSpecificMethods(methodNames, ip);
        } else {
            throw new NameServiceBindException(String.format("Invalid method type: %s", type));
        }

        return id;
    }

    @Override
    public String lookup(String methodName) throws NameServiceLookupException {
        Long methodId = loadBalancer.choose(methodName);

        if (methodId == null) {
            throw new NameServiceLookupException(String.format("No Entry for methodName %s", methodName));
        }
        return nameServiceData.getMethodFromMainMap(methodId).getIp();
    }

    @Override
    public String lookup(String methodName, long stateId) throws NameServiceLookupException {
        Integer methodType = nameServiceData.getMethodType(methodName);

        if (methodType == STATE_INITIALIZING) {
            Long methodId = loadBalancer.choose(methodName);
            MethodObject methodObject = nameServiceData.getMethodFromMainMap(methodId);

            List<Long> methodBundleIds = nameServiceData.getMethodIdsForBundleId(methodObject.getBundleId());

            for (Long id : methodBundleIds) {
                String bundleMethodName = nameServiceData.getMethodFromMainMap(id).getMethodName();
                nameServiceData.removeFromStateMap(stateId, bundleMethodName);
                nameServiceData.addToStateMap(stateId, bundleMethodName, id);
            }

            return methodObject.getIp();

        } else if (methodType == STATEFUL) {
            Long methodId = nameServiceData.getMethodIdFromStateMap(stateId, methodName);
            if (methodId == null) {
                throw new NameServiceLookupException(String.format("No entry in state map for stateId %s and methodName %s", stateId, methodName));
            } else {
                return nameServiceData.getMethodFromMainMap(methodId).getIp();
            }
        } else {
            throw new NameServiceLookupException(String.format("No entry in main map for %s", methodName));
        }
    }

    @Override
    public String lookupSpecific(String methodName, long specificId) throws NameServiceLookupException {
        String ip =  nameServiceData.getFromSpecificMap(methodName, specificId);

        if (ip == null) {
            throw new NameServiceLookupException(String.format("No Entry for specificId %s and methodName %s", specificId, methodName));
        }

        return ip;
    }
}
