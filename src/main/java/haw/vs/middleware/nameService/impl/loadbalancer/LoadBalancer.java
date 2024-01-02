package haw.vs.middleware.nameService.impl.loadbalancer;

import haw.vs.middleware.nameService.impl.NameServiceData;

import java.util.HashMap;
import java.util.Map;

public class LoadBalancer implements ILoadBalancer {
    private final NameServiceData nameServiceData;

    private Map<String, Integer> counterMap;

    public LoadBalancer(NameServiceData nameServiceData) {
        this.nameServiceData = nameServiceData;
        this.counterMap = new HashMap<>();
    }

    /**
     * Chooses the methodId for the right method in the mainMap of NameServiceData.
     * Follows a simple round-robin principle.
     *
     * @param methodName - has to be present in NameServiceData
     * @return methodId
     */
    @Override
    public Long choose(String methodName) {
        return nameServiceData.getMethodIdsForMethodName(methodName).get(getMethodIdIndex(methodName));
    }

    private int getMethodIdIndex(String methodName) {
        counterMap.putIfAbsent(methodName, 0);
        int index = counterMap.get(methodName);
        int countOfMethods = nameServiceData.getMethodIdsForMethodName(methodName).size();
        int newIndex = (index+1)  % countOfMethods;
        counterMap.put(methodName, newIndex);
        return index;
    }
}
