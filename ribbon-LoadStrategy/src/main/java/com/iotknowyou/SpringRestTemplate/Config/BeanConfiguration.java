package com.iotknowyou.SpringRestTemplate.Config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    /*
        在配置注解类中添加 @LandBalanced 的注解就可以 使得 RestTemplate 和 Eureka 进行结合。
           这里的主要的逻辑是： 给RestTemplate 添加 拦截器，在请求之前对请求的地址进行替换
           或者 根据具体的负载均衡策略选择服务器，然后再去调用。
    */

    /*
        Ribbon 可以实现自定义的负载均衡策略


     • BestAvailabl ：选择一个最小的并发请求的 Server，逐个考察 Server，
     如果 Server 被 标记为错误了，则跳过，然后再选择其中 ActiveRequestCount 最小的 Server。

     • AvailabilityFilteringRule ：过滤掉那些一直连接失败的且被标记为 circuit tripped 的后端 Server，
     并过滤掉那些高并发的后端 Server 或者使用一个 AvailabilityPredicate 来 包含过滤 Server 的逻辑。
     其实就是检查 Status 里记录的各个 Server 的运行状态。

     • ZoneAvoidanceRule ：使用 ZoneAvoidancePredicate 和 AvailabilityPredicate 来判断是否选择某个 Server，
     前一个判断判定一个 Zone 的运行性能是否可用，剔除不可用的 Zone （的所有 Server), Availability Predicate 用于过滤掉连接数过多的 Server。

     • RandomRule：随机选择一个 Server。

     • RoundRobinRule：轮询选择，轮询 index，选择 index 对应位置的 Server。

     • RetryRule ：对选定的负载均衡策略机上重试机制，也就是说当选定了某个策略进行请求负载时在一个配置时间段内
     若选择 Server 不成功， 则一直尝试使用 subRule 的 方式选择一个可用的 Server。

     • Response Time WeightedRule：作用同 WeightedResponseTimeRule,Response Time Weighted Rule 后来改名为 WeightedResponseTimeRule。

     • WeightedResponseTimeRule ：根据响应时间分配一个 Weight （权重），响应时间越长， Weight 越小，被选中的可能性越低。

    */

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }



}
