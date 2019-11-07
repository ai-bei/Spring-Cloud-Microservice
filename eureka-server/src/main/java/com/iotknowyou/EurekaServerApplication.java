package com.iotknowyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/*
 *
 * 	pom文件中引入依赖，
 * 	开启	@EnableEurekaServer 注解
 *   配置 application.properties 文件
 * 	就可以使用  Eureka Server 了
 *
 *
 * */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
