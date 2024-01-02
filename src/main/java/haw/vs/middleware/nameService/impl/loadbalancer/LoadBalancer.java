package haw.vs.middleware.nameService.impl.loadbalancer;

import haw.vs.middleware.nameService.impl.NameServiceData;

public class LoadBalancer implements ILoadBalancer {
    private final NameServiceData nameServiceData;

    public LoadBalancer(NameServiceData nameServiceData) {
        this.nameServiceData = nameServiceData;
    }

    @Override
    public long choose(String methodName) {
        return 0;
    }
}
