package com.iotknowyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulDemoApplication.class, args);
    }
}
