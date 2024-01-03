package haw.vs.middleware.nameService.impl.loadbalancer;

import haw.vs.middleware.nameService.impl.NameServiceData;

public class LoadBalancerFactory {
    public static ILoadBalancer getLoadBalancer() {
        return new LoadBalancer(NameServiceData.getInstance());
    }
}
