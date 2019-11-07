package com.iotknowyou.EurekaClientArticleService.Config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    /*
    *
    *   使用 @LoadBalanced 注解，这个注解会自动构造 LoadBalancerClient 接口的实现类并且 注册到spring容器中
    *
    * */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
