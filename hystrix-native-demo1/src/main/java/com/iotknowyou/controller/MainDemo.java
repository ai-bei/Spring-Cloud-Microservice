package com.iotknowyou.controller;

import com.iotknowyou.config.MyHystrixCommand;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainDemo {

    /* 同步调用*/
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String result = new MyHystrixCommand("LiuRongHua").execute();
        /* 构造函数中设置的组名变成了线程的名字。 */
        System.out.println("同步调用的输出结果："+result);

        Future<String> result1 = new MyHystrixCommand("LiuRongHua1").queue();
        System.out.println("异步调用的输出结果："+result1.get());
    }
}
