package com.iotknowyou.config;

import com.iotknowyou.auth.FeignBasicAuthRequestInterceptor;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/*
*   配置日志级别
*
* */
@Configuration
public class FeignConfiguration {


/*

    Spring Cloud 在 Feign 的基础上做了扩展，可以让 Feign 支持 Spring MVC 的注解来调 用。
    原生的 Feign 是不支持 Spring MVC 注解的 ，原生的使用方法在后面我们会讲解。
    如果 你想在 Spring Cloud 中使用原生的注解方式来定义客户端也是可以的 ，
    通过配置契约来改 变这个配置， Spring Cloud 中默认是 SpringMvcContract



    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }
*/






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


    /*  自带的 权限认证的权限控制和 自定义的 权限认证的 同时存在，多个拦截器都会执行拦截 。  */

    /*
    通常我们调用的接口都是有权限控制的， 很多时候可能认证的值是通过参数去传递的，
    还有就是通过请求头去传递认证信息，比如 Basic 认证方式。
    在 Feign 中我们可以直接配置 Basic 认证，
    */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }

    /**
     *
     * 或者你可以自定义属于自己的认证方式，其实就是自定义一个请求拦截器。
     * 在请求之 前做认证操作，然后往请求头中设置认证之后的信息。
     * 通过实现 Requestlnterceptor 接口来 自定义认证方式。
     * 然后我们将配置改成我们自定义的就可以了，这样当 Feign 去请求接口的时候，
     * 每次请 求之前都会进入 FeignBasicAuthRequestlnterceptor 的 apply 方法中，
     * 在其里面就可以做属于 你的逻辑了，
     * @return
     */

    @Bean
    public FeignBasicAuthRequestInterceptor feignBasicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }

    /**
     * 通过 Options 可以配置连接超时时间和读取超时时间,
     * Options 的第 一个参数是连接超时时间（ ms），默认值是 10 x 1000 ；
     * 第二个是取超时时间（ ms），默认值 是 60 × 1000。
     * @return
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }




    /*
    Feign 中提供了自定义的编码解码器设置，同时提供了很多种编码器的实现，
    比如 Gson、 Jaxb、 Jackson。
    我们可以用不同的编码解码器来处理数据的传输。
    如果你想传输 XML 格式的数据，
    可以 自定义 XML 编码解码器来实现获取使用官方提供的 Jaxb。
    */

    /*配置编码解码器只需要在 Feign 的配置类中注册 Decoder 和 Encoder 这两个类即可*/

   /* @Bean
    public MyDecoder decoder(){
        return  new MyDecoder();
    }

    @Bean
    public Encoder encoder(){
        return new MyEncoder();
    }
    */
}
