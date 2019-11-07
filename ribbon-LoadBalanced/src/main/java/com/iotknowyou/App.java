package com.iotknowyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * RestTemplate客户端示列
 *
 * @author LiuRongHua
 *
 *
 * @date 2019-10-22
 *
 */

/*
 *通过 @EnableDiscoveryClient 这个注解可以表示当前服务是一个 Eureka 的客户端
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}