package haw.vs.middleware.nameService.impl.loadbalancer;

public interface ILoadBalancer {
    Long choose(String methodName);
}
