package haw.vs.middleware.nameService.impl;

import haw.vs.middleware.nameService.api.INameService;
import haw.vs.middleware.nameService.impl.loadbalancer.ILoadBalancer;

import java.util.List;

public class NameService implements INameService {
    private final NameServiceData nameServiceData;
    private final ILoadBalancer loadBalancer;

    public NameService(NameServiceData nameServiceData, ILoadBalancer loadBalancer) {
        this.nameServiceData = nameServiceData;
        this.loadBalancer = loadBalancer;
    }

    @Override
    public long bind(List<String> methodNames, int type) {
        return 0;
    }

    @Override
    public String lookup(String methodName) {
        return null;
    }

    @Override
    public String lookup(String methodName, long stateId) {
        return null;
    }

    @Override
    public String lookup(long specificId) {
        return null;
    }
}
