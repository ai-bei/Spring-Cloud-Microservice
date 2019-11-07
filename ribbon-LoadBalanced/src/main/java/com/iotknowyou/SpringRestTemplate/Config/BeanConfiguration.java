package com.iotknowyou.SpringRestTemplate.Config;

import com.iotknowyou.SpringRestTemplate.Annotation.MyLoadBalanced;
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

    @Bean
    @MyLoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
