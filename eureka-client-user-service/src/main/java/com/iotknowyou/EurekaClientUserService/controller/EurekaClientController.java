package com.iotknowyou.EurekaClientUserService.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaClientController {

    /*Qualifier的意思是合格者，通过这个标示，表明了哪个实现类才是我们所需要的，
    添加@Qualifier注解，需要注意的是@Qualifier的参数名称为我们之前定义@Service注解的名称之一。*/
    @Qualifier("eurekaClient")
    /*
     *   我们可以通过 EurekaClient获取我们想要的数据
     */
    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/article/info")
    public Object serviceUrl(){
        return eurekaClient.getInstancesByVipAddress("eureka-client-user-service",false);
    }
}
