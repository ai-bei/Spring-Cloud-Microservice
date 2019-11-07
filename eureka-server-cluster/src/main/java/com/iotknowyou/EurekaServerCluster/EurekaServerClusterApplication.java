package com.iotknowyou.EurekaServerCluster;

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
public class EurekaServerClusterApplication {

    /*
    * 在生成环境中，我们必须搭建一个集群来保证高可用。Eureka 的集群搭建方式很简单，
    * 每一台Eureka只需要在配置中指定另外多个Eureka的地址就可以实现一个集群的搭建。
    *
    * 二台机器 ： 将master注册到slaveone ，将 slaveone 注册到 master
    *
    * 三台机器 ： 将 master 注册到 slaveone 和 slavetwo 中
    *           将 slaveone 注册到 master 和 slavetwo 中
    *           将 slavetwo 注册到 master 和 slaveone 中
    * 以此类推。。。。
    *
    *
    *
    * */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerClusterApplication.class, args);
    }

}