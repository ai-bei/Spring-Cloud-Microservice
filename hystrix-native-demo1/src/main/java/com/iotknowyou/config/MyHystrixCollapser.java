package com.iotknowyou.config;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
    Hystrix 支持将多个请求自动合并为一个请求，利用这个功能可以节 省网络开销 ，
    比如每个请求都要通过网络访问远程资源。
    如果把多个请求合并为一个一起 执行，将多次网络交互变成一次，则会极大节省开销。
 */
public class MyHystrixCollapser extends HystrixCollapser<List<String>, String, String> {
    private final String name;

    public MyHystrixCollapser(String name) {
        this.name = name;
    }

    @Override
    public String getRequestArgument() {
        return name;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(final Collection<CollapsedRequest<String, String>> requests) {
        return new BatchCommand(requests);
    }

    @Override
    protected void mapResponseToRequests(List<String> batchResponse,
                                         Collection<CollapsedRequest<String, String>> requests) {
        int count = 0;
        for (CollapsedRequest<String, String> request : requests) {
            request.setResponse(batchResponse.get(count++));
        }
    }

    private static final class BatchCommand extends HystrixCommand<List<String>> {

        private final Collection<CollapsedRequest<String, String>> requests;

        private BatchCommand(Collection<CollapsedRequest<String, String>> requests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey")));
            this.requests = requests;
        }

        /*
            通过 HystrixCollapser 可以将多个任务合并到一起执行。
            任务的执行是在 run 方法中去做的
        */

        @Override
        protected List<String> run() {
            System.out.println("真正执行请求.......");
            ArrayList<String> response = new ArrayList<String>();
            for (CollapsedRequest<String, String> request : requests) {
                response.add("返回结果: " + request.getArgument());
            }
            return response;
        }
    }
}
