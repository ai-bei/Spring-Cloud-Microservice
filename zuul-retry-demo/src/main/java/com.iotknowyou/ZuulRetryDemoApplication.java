package com.iotknowyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;



/*
当 Zuul 集成 Eureka 之后，其实就可以为 Eureka 中所有的服务进行路由操作了，
默认 的转发规则就是“ API 网关地址 ＋访问的服务名称 ＋接 口 URI” 。
在给服务指定名称的时－ 候，应尽量短一点，这样的话我们就可以用默认的路由规则进行请求，
不需要为每个服务 都定一个路由规则，这样就算新增了服务， API 网关也不用修改和重启了

*/


// ＠EnableZuulProxy 已经自带了＠EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulRetryDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulRetryDemoApplication.class, args);
    }
}
