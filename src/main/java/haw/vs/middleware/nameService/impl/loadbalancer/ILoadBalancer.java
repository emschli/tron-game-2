package haw.vs.middleware.nameService.impl.loadbalancer;

public interface ILoadBalancer {
    long choose(String methodName);
}
