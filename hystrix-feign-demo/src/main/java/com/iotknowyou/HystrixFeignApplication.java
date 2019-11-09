package com.iotknowyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;



/*
   示例： springcloud 集成使用 Hystrix
 */
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@SpringBootApplication
public class HystrixFeignApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(HystrixFeignApplication.class, args);
    }
}
