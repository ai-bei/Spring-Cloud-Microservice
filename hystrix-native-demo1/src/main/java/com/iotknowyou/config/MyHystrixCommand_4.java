package com.iotknowyou.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;


/*
缓存在开发中经常用到，我们常用 Redis 这种第三方的缓存数据库来对数据进行缓存处 理。
Hystrix 中也为我们提供了方法级别的缓存。 通过重写 getCacheKey 来判断是否返回缓 存的数据，
getCacheKey 可以根据参数来生成，这样同样的参数就可以都用到缓存了。
改造之前的 MyHystrixCommand，在其中增加 getCacheKey 的重写实现，
*/
public class MyHystrixCommand_4 extends HystrixCommand<String> {

    private final String name;


    public MyHystrixCommand_4(String name) {

        /* 信号量策略配置方法如代码 */
        /*super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(
                                        HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE

                                )
                )
        );*/

        /* 线程隔离策略配置方法如代码 */
        super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(
                                        HystrixCommandProperties.ExecutionIsolationStrategy.THREAD
                                )
                /*我们可以通过 andThreadPoo!PropertiesDefaults 配置线程池的一些参数*/
                ).andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(10)
                                .withMaxQueueSize(100)
                                .withMaximumSize(100)
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

    @Override
    protected String getCacheKey() {
        return String.valueOf(this.name);
    }
}
