package haw.vs.middleware.nameService.impl;

import haw.vs.middleware.nameService.impl.loadbalancer.LoadBalancerFactory;


public class NameServiceFactory {

    public static INameService getNameService() {
       return new NameService(NameServiceData.getInstance(), LoadBalancerFactory.getLoadBalancer());
    }

}
