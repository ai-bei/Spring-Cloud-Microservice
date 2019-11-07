package com.iotknowyou.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/*
*   配置日志级别
*
* */
@Configuration
public class FeignConfiguration {

    /*
    通过源码可以看到 日志等级有 4 种 ， 分别是：
    • NONE： 不输出 日志。
    • BASIC： 只输出请求方法的 URL 和响应的状态码以及接口执行的时间。
    • HEADERS：将 BASIC 信息和请求头信息输出 。
    •FULL：输出完整的请求信息。
    */

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
