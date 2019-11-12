package com.iotknowyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
    1，@EnableDiscoveryClient注解是基于spring-cloud-commons依赖，并且在classpath中实现； 
    2，@EnableEurekaClient注解是基于spring-cloud-netflix依赖，只能为eureka作用；
    其实用更简单的话来说，就是如果选用的注册中心是eureka，那么就推荐@EnableEurekaClient，
    如果是其他的注册中心，那么推荐使用@EnableDiscoveryClient
    通过 @EnableDiscoveryClient 这个注解可以表示当前服务是一个 Eureka 的客户端
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayDemoApplication.class,args);
    }
}
