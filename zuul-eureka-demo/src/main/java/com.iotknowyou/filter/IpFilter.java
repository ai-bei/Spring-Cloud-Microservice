package com.iotknowyou.filter;

import com.iotknowyou.base.ResponseCode;
import com.iotknowyou.base.ResponseData;
import com.iotknowyou.util.IpUtils;
import com.iotknowyou.util.JsonUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.micrometer.core.instrument.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/*
    Zuul 中的过滤器总共有 4 种类型，每种类型都有对应的使用场景。
    • pre ：可以在请求被路由之前调用。 适用于身份认证的场景，认证通过后再继续执行 下面的流程。
    • route ：在路由请求时被调用。 适用于灰度发布场景，在将要路由的时候可以做一些 自定义的逻辑。
    • post：在 route 和 error 过滤器之后被调用。 速种过滤器将请求路由到达具体的服务之 后执行。 适用于需要添加响应头，记录响应日志等应用场景。
    • error：处理请求时发生错误时被调用。 在执行过程中发送错误时会进入 error 过滤器， 可以用来统一记录错误信息。


*/

/*
    自定义的 filter ：

    自定义过滤器需要继承 ZuulFilter， 并且需要实现下面几个方法：
    • shouldFilter ： 是否执行该过滤器， true 为执行， false 为不执行，这个也可以利用配 置中心来实现，达到动态的开启和关闭过滤器。
    • filterType ：过滤器类型，可选值有 pre、 route、 post、 error。
    • filterOrder：过滤器的执行顺序，数值越小，优先级越高。
    • run ：执行自己的业务逻辑，

    过滤器定义完成之后我们需要配置过滤器才能生效

 */







public class IpFilter extends ZuulFilter {

    // IP黑名单列表
    private List<String> blackIpList = Arrays.asList("127.0.0.1");

    public IpFilter() {
        super();
    }

    @Override
    public boolean shouldFilter() {
        /*
            解决所有的过滤器都会执行的方法：
                通过 shouldFilter 来处理，
                即在拦截之后通过数据传递的方式告诉下一个过滤器是否要执行。
        */
        RequestContext ctx = RequestContext.getCurrentContext();
        Object success = ctx .get("isSuccess");
        return success == null ? true : Boolean.parseBoolean(success.toString());
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }


    /*

    本段代码中是通过判断请求的 IP 是否在黑名单中，决定 是否进行拦截的。
    BasicConf是一个配置类，里面有个字段是 IP 的黑名单，判断条 件成立之后，
    通过设置 ctx.setSendZuulResponse(false），告诉 Zuul 不需要将当前请 求转发到后端的服务了。
    通过 setResponseBody 返回数据给客户端。

    */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String ip = IpUtils.getIpAddr(ctx.getRequest());
        // 在黑名单中禁用
        if (StringUtils.isNotBlank(ip) && blackIpList.contains(ip)) {

            /* 模拟一个异常，测试异常过滤器*/
            System.out.println(2/0);

            //告诉 Zuul 不需要将当前请求转发到后端的服务
            ctx.setSendZuulResponse(false);
            // 告诉zuul ， 拦截本地转发的请求，但我们配置了 forword:/local 的路由，
            // ctx.setSendZuulResponse(false); 对 forward 不起作用
            // 需要设置 ctx.set("sendForwardFilter.ran", true);
            ctx.set("sendForwardFilter.ran", true);
            /*
            在 RequestContext 中设置一个值来标识是否成功，
                当为 true 的时候，后续的过滤器才 执行，
                若为 false 则不执行。 利用这种方法，
            在后面的过滤器就需要用到这个值来决定自己 此时是否需要执行，
            */
            ctx.set("isSuccess", true);
            ResponseData data = ResponseData.fail("非法请求", ResponseCode.NO_AUTH_CODE.getCode());
            ctx.setResponseBody(JsonUtils.toJson(data));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        return null;
    }

}

