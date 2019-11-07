package com.iotknowyou.springCloudEurekaServer.ListenerConfig;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/*
*   配置 监控服务的上下线
* */
@Component
public class EurekaStateChangeListener {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent){
        System.err.println(
                eurekaInstanceCanceledEvent.getServerId() +"\t"
                +eurekaInstanceCanceledEvent.getAppName() +"服务下线。。。"
        );
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent eurekaInstanceRegisteredEvent){
        InstanceInfo instanceInfo = eurekaInstanceRegisteredEvent.getInstanceInfo();
        System.err.println(
                instanceInfo.getAppName() +"进行注册了。。。"
        );
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent eurekaInstanceRenewedEvent){
        System.err.println(
                eurekaInstanceRenewedEvent.getServerId() +"\t"
                        +eurekaInstanceRenewedEvent.getAppName() +"服务进行续约。。。"
        );
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent eurekaRegistryAvailableEvent){
        System.err.println(
                "注册中心启动了。。。。。。"
        );
    }

    @EventListener
    public void listen(EurekaServerStartedEvent eurekaServerStartedEvent){
        System.err.println(
                "Eureka  Server  启动。。。。。。"
        );
    }
}
