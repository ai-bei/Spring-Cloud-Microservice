package com.iotknowyou.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;


/*
    首先需要继承 HystrixCommand， 通过构造函数设置一个 Groupkey。
    具体的逻辑 在 run 方法中，我们返回了一个当前线程名称的值
*/
public class MyHystrixCommand extends HystrixCommand<String> {

    private final String name;

    public MyHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        System.err.println("get data");
        return this.name + ":" + Thread.currentThread().getName();
    }

}
