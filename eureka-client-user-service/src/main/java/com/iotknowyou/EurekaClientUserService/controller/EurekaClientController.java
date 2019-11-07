package com.iotknowyou.EurekaClientUserService.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return eurekaClient.getInstancesByVipAddress("eureka-client-user-service",false);
    }
}
