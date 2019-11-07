package com.iotknowyou.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;


/**
 *
 *
 * 或者你可以自定义属于自己的认证方式，其实就是自定义一个请求拦截器。
 * 在请求之 前做认证操作，然后往请求头中设置认证之后的信息。
 * 通过实现 Requestlnterceptor 接口来 自定义认证方式
 */
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    public FeignBasicAuthRequestInterceptor() {

    }

    @Override
    public void apply(RequestTemplate template) {
        System.err.println("在这里可以进行权限认证");
        System.err.println("进入拦截器了");
    }


}

