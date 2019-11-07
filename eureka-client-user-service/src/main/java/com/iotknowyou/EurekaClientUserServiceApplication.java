package com.iotknowyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
/*
 *   通过 @EnableDiscoveryClient 这个注解可以表示当前服务是一个 Eureka 的客户端
 * */
@EnableDiscoveryClient
public class EurekaClientUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientUserServiceApplication.class, args);
    }

}
