package com.iotknowyou.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;


/*
下面我们通过增加执行时间模拟调用失败的情况，
首先改造 MyHystrixCommand,增加getFallback 方法返回回退内容，如
*/
public class MyHystrixCommand_1 extends HystrixCommand<String> {

    private final String name;

    public MyHystrixCommand_1(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        try {
            Thread.sleep(1000*10);
            System.err.println("get data");
        } catch (InterruptedException e) {
            System.err.println("get data Error !!!!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
        }
        return this.name + ":" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "触发了 回退！！！！";
    }
}
