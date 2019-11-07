package com.iotknowyou.SpringRestTemplate.Rule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/*
    如何实现自定义的负载均衡策略

    通过实现 IRule 接口可以自定义负载策略，主要的选择服务逻辑在 choose 方法中。

*/


public class MyRule implements IRule {

    private ILoadBalancer lb;

    @Override
    public Server choose(Object key) {
        List<Server> servers = lb.getAllServers();
        for (Server server : servers) {
            System.out.println("执行了MyRule ：" + server.getHostPort());
        }
        System.out.println("调用的服务是："+servers.get(0).toString());
        return servers.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }

}
