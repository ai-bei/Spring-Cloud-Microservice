package com.iotknowyou.EurekaClientArticleService.Controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EurekaClientController {
    /*
    *   我们可以通过 EurekaClient获取我们想要的数据
    *
    * */


    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/article/info")
    public Object serviceUrl(){
        Map reslutMap = new HashMap(16);
        reslutMap.put("eureka-client-article-service",eurekaClient.getInstancesByVipAddress("eureka-client-article-service",false));
        reslutMap.put("eureka-client-user-service",eurekaClient.getInstancesByVipAddress("eureka-client-user-service",false));
        return reslutMap;
    }
}
