package com.iotknowyou.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;


/*
下面我们通过增加执行时间模拟调用失败的情况，
首先改造 MyHystrixCommand,增加getFallback 方法返回回退内容，如
*/
public class MyHystrixCommand_2 extends HystrixCommand<String> {

    private final String name;

    /* 信号量策略配置方法如代码 */
    public MyHystrixCommand_2(String name) {
        super(HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(
                                        HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE

                                )
                )
        );
        this.name = name;
    }

    @Override
    protected String run() {
        /*try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            System.err.println("get data Error !!!!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
        }*/
        System.err.println("get data");
        return this.name + ":" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "触发了 回退！！！！";
    }
}
