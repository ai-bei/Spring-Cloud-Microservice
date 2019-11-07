package com.iotknowyou.EurekaClientArticleService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ArticleController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/article/callhello")
    public String callHello(){
        /*
        *   在 BeanConfiguration中 使用了@LoadBalanced 注解
        *   我们不再直接写固定的地址，而是写成服务的名称
        *
        * */
       // return restTemplate.getForObject("http://localhost:8081/user/hello",String.class);
        return restTemplate.getForObject("http://eureka-client-user-service/user/hello",String.class);
    }
}
