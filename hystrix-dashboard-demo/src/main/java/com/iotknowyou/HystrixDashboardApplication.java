package com.iotknowyou;

/*

*/
/*
   示例： springcloud 集成使用 Dashboard
 *//*

@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashboardApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
}
*/


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/*
   示例： springcloud 集成使用 Dashboard 和 Turbine

   Turbine 是聚合服务器发送事件流数据的一个工具。 Hystrix 只能监控单个节点，然后通 过 dashboard 进行展示。
   实际生产中都为集群，这个时候我们可以通过 Turbine 来监控集群 下 Hystrix 的 metrics 情况，通过 Eureka 来发现 Hystrix 服务。
 */
@EnableDiscoveryClient
@EnableTurbine
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashboardApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
}
