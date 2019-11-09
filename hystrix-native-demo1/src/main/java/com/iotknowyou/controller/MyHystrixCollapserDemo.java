package com.iotknowyou.controller;

import com.iotknowyou.config.MyHystrixCollapser;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MyHystrixCollapserDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> f1 = new MyHystrixCollapser("LiuRongHua").queue();
        Future<String> f2 = new MyHystrixCollapser("LiuRongHua_Kevin").queue();
        System.out.println(f1.get()+"="+f2.get());
        context.shutdown();

    }
}
