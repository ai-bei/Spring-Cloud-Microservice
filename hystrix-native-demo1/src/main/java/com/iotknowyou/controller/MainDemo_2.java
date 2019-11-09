package com.iotknowyou.controller;

import com.iotknowyou.config.MyHystrixCommand_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainDemo_2 {


    /*通过这个名称就可以确定当前是线程隔离还 是信号量隔离。*/
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String result = new MyHystrixCommand_2("LiuRongHua1").execute();
        /* 构造函数中设置的组名变成了线程的名字。 */
        /* 同步调用*/
        System.out.println("同步调用的输出结果："+result);

        /* 异步调用*/
        Future<String> result1 = new MyHystrixCommand_2("LiuRongHua2").queue();
        System.out.println("异步调用的输出结果："+result1.get());
    }
}
