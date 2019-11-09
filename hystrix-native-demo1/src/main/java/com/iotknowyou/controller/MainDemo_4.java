package com.iotknowyou.controller;

import com.iotknowyou.config.MyHystrixCommand_4;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainDemo_4 {


    /*通过这个名称就可以确定当前是线程隔离还 是信号量隔离。*/
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        /*
        Caused by: java. lang. IllegalStateException:
        Request caching is not available. Maybe you need to initialize the HystrixRequestContext?
        根据错误提示可以知道， 缓存的处理取决于请求的上下文，我们必须初始化 Hystrix RequestContext。
        */
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        String result = new MyHystrixCommand_4("LiuRongHua1").execute();
        /* 构造函数中设置的组名变成了线程的名字。 */
        /* 同步调用*/
        System.out.println("同步调用的输出结果："+result);

        /* 异步调用*/
        Future<String> result1 = new MyHystrixCommand_4("LiuRongHua1").queue();
        System.out.println("异步调用的输出结果："+result1.get());

        context.shutdown();
    }
}
