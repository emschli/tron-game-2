package haw.vs.middleware.nameService.impl;

import haw.vs.middleware.common.properties.MiddlewareAppType;
import haw.vs.middleware.common.properties.MiddlewarePropertiesException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesHelper;
import haw.vs.middleware.nameService.api.INameService;
import haw.vs.middleware.nameService.impl.loadbalancer.LoadBalancerFactory;


public class NameServiceFactory {

    public static INameService getNameService() {
        MiddlewareAppType appType;
        try {
             appType = MiddlewarePropertiesHelper.getMiddlewareAppType();
        } catch (MiddlewarePropertiesException e) {
            throw new RuntimeException(e);
        }

        switch (appType) {
            case MIDDLEWARE -> {
                return new NameServiceCachingProxy();
            }
            case NAME_SERVICE -> {
                return new NameService(NameServiceData.getInstance(), LoadBalancerFactory.getLoadBalancer());
            }
            default -> throw new RuntimeException("Cannot get Instance of NameService!");
        }

    }

}
