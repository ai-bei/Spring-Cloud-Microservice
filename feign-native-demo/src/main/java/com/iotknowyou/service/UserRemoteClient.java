package com.iotknowyou.service;

import com.iotknowyou.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/*


首先我们来看接口上加的＠FeignClient 注解。
这个注解标识当前是一个 Feign 的客户端， value 属性是对应的服务名称，也就是你需要调用哪个服务中的接口，
path 就是接口中 URI 统一的前缀。
定义方法时直接复制接口的定义即可，当然还有一种做法， 就是将接口单独抽出来定义，然后在 Controller 中实现接口 。
在调用的客户端中也实现了接口，从而达到接口共用的目的。
我这里的做法是不共用的，单独创建一个 API Client 的公共项目，基于约定的模式，
每写一个接口就要对应写一个调用的 Client，后面打成公共的 jar，这样无论是哪个项目需 要调用接口，引人公共的接口 SDKjar 即可，不用重新定义一遍了。
定义之后可以直接通过注入 UserRemoteClient 来调用，对于开发人员来说就像调用本 地方法一样。

 */






/*  配置类建好后，我们需要在 Feign Client 中的 ＠FeignClient 注解中指定使用的配置类，  */
@FeignClient(value = "eureka-client-user-service",configuration = FeignConfiguration.class)
public interface UserRemoteClient {

    @GetMapping("/user/hello")
    String hello();
}
