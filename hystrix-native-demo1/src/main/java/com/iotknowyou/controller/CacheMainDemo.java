package com.iotknowyou.controller;

import com.iotknowyou.config.ClearCacheHystrixCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CacheMainDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        String result = new ClearCacheHystrixCommand("LiuRongHua1").execute();
        System.out.println(result);
        /* 清除缓存 */
        ClearCacheHystrixCommand.flushCache("LiuRongHua1");
        Future<String> future = new ClearCacheHystrixCommand("LiuRongHua1").queue();
        System.out.println(future.get());
        context.shutdown();

    }
}
