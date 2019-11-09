package com.iotknowyou.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/*
FallbackFactory 可以看到异常信息返回在结果里面了，
FallbackFactory 和 Fall back 唯一的区别就在这里。
 */

//fallback=UserRemoteClientFallback.class
@FeignClient(value="eureka-client-user-service", fallbackFactory=UserRemoteClientFallbackFactory.class)
public interface UserRemoteClient {

    @GetMapping("/user/hello")
    String hello();

}

/*
HystrixCommand 中除了 fallbackMethod 还有很多的配置，下面我们来看看这些配置：
• hystrix.command.default.execution.isolation.strategy ： 该配置用来指定隔离策略，具 体策略有下面 2 种。
    • THREAD：线程隔离，在单独的线程上执行，并发请求受线程池大小的控制。
    • SEMAPHORE ：信号量隔离，在调用线程上执行，并发请求受信号量计数器的 限制。
• hystrix. command.default. execution. isolation. thread. timeoutlnMilliseconds ： 该配置用 于 Hystrix Command 执行的超时时间设置，
    当 HystrixCommand 执行的时间超过了该 配置所设置的数值后就会进入服务降级处理，单位是毫秒，默认值为 1000。
• hystrix.command.default.execution.timeout.enabled ：
    该配置用于确定是否启用 execution.isolation. thread. timeoutlnMilliseconds 设置的超时时间，默认值为 true。
    设置为 false 后 execution.isolation.thread.timeoutinMilliseconds 配置也将失效。
• hystrix. command.default. execution. isolation.thread.interruptOn Timeout： 该配置用于确 定 HystrixCommand 执行超时后是否需要中断它，默认值为 true。
• hystrix.command.default.execution.isolation.thread.interruptOnCancel ：该配置用于确 定 HystrixCommand 执行被取消时是否需要中断它，默认值为 false。
• hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests ： 该配 置用于确定 Hystrix 使用信号量策略时最大的并发请求数。
• hystrix. command.default. fallback.isolation.semaphore. max Concurren tRequests： 该配置 用于如果并发数达到该设置值，请求会被拒绝和抛出异常并且 fallback 不会被调用， 默认值为 10。
• hystrix.command.default.fallback.enabled ： 该配置用于确定当执行失败或者请求被拒 绝时，是否会尝试调用 hystrixCommand.getFallback（），默认值为 true。
• hystrix.command.default.circuitBreaker.enabled ： 该配置用来跟踪 circuit 的健康性， 如果未达标则让 request 短路，默认值为 true。
• hystrix.command.default.circuitBreaker.requestVolumeThreshold ：该配置用于设置一 个 rolling window 内最小的请求数。 如果设为 20 ，那么当一个 rolling window 的时－ 间内（比如说 l 个 rolling window 是 10 秒）收到 19 个请求， 即使 19 个请求都失败， 也不会触发 circuit break， 默认值为 20。
• hystrix.command.default.circuitBreaker.sleepWindowlnMilliseconds ： 该配置用于设置 一个触发短路的时间值，当该值设为 5000 时，则当触发 circuit break 后的 5000 毫秒 内都会拒绝 request，也就是 5000 毫秒后才会关闭 circuit。 默认值为 5000。
• hystrix.command.default.circ山tBreaker.errorThresholdPercentage ： 该配置用于设置错 误率阔值，当错误率超过此值时，所有请求都会触发 fallback，默认值为 50。
• hystrix.command.default.circuitBreaker.forceOpen ：如果配置为 true，将强制打开熔断 器，在这个状态下将拒绝所有请求，默认值为 false。
• hystrix.command.default.circuitBreaker.forceClosed ：如果配置为 true，则将强制关闭 熔断器，在这个状态下，不管错误率有多高，都允许请求，默认值为 false。
• hystrix.command.default.metrics.rollingStats.timelnMilliseconds ： 设置统计的时间窗 口值，单位为毫秒。 circuit break 的打开会根据 1 个 rolling window 的统计来计算。 若 rolling window 被设为 10 000 毫秒， 则 rolling window 会被分成多个 buckets，
    每个 bucket 包含 success、 failure、 timeout、 rejection 的次数的统计信息。 默认值为 10 000 毫秒。
• hystrix.command.default.metrics.rollingStats.numBuckets ：设置 一 个 rolling window 被划分的数量，若 numBuckets=l 0、 rolling window= 10 000 ，那么一个 bucket 的时 间即 l 秒。
    必须符合 rolling window % numberBuckets == 0。 默认值为 10。
• hystrix.command.default.metrics.rollingPercentile.enabled ： 是否开启指标的计算和跟 踪，默认值为 true。
• hystrix.command.default.metrics.rollingPercentile.timelnMilliseconds ：设置 rolling percentile window 的时间， 默认值为 60 000 毫秒。
• hystrix.command.default.metrics.rollingPercentile.numBuckets ：设置 rolling percentile window 的 numberBuckets ，默认值为 6。
• hystrix.cornrnand.defau It.metrics. ro llingPercentile. bucketS ize ：如果 bucket size= 100 、 window= IO 秒，若这 10 秒里有 500 次执行，只有最后 100 次执行会被统计到 bucket 里去。 增加该值会增加内存开销及排序的开销。 默认值为 100。
• hystrix.command.default.metrics.healthSnapshot.intervallnMilliseconds：用来计算影响 断路器状态的健康快照的间隔等待时间，默认值为 500 毫秒。
• hystrix.command.default.requestCache.enabled ： 是否开启请求缓存功能，默认值为true。
• hystrix.cornmand.default.requestLog.enabled ：记录日志到 HystrixRequestLog， 默认 值为 true。
• hystrix.collapser.default.maxRequestslnBatch ： 单次批处理的最大请求数，达到眩数 量触发批处理， 默认为 Integer.MAX_VALUE。
• hystrix.col Japser.default. timerDelay InMilliseconds ：触发批处理的延迟，延迟也可以 为创建批处理的时间与该值的和，默认值为 10 毫秒。
• hystrix. collapser. defau It. requestCache. enabled ：是否启用对 HystrixCollapser.execute() 和 Hystrix Collapser. queue（）的请求缓存， 默认值为 true。
• hystrix.threadpool.default.coreSize ： 并发执行的最大线程数，默认值为 10。
• hystrix.threadpool.default.maxQueueSize : BlockingQueue 的最大队列数。
    当设为 － 1 时，会使用 SynchronousQueue ；值为正数时， 会使用 LinkedBlcokingQueue。
    该设 置只会在初始化时有效，之后不能修改 threadpool 的 queue size。 默认值为一1 。
• hystrix.threadpool.default.queueSizeRejectionThreshold ：即使没有达到 maxQueueSize, 但若达到 queueSizeRejectionThreshold 该值后，请求也会被拒绝。
    因为 maxQueueSize 不能被动态修改，而 queueSizeRejectionThreshold 参数将允许我们动态设置该值。 if maxQueueSize == -1 ， 该字段将不起作用。
• hystrix. threadpool. defau It. keepAli veTimeMinutes ：设置存活时间，单位为分钟。
    如果 coreSize 小于 maximumSize，那么该属性控制一个线程从实用完成到被释放的时间。 默认值为 1 分钟。
• hystrix. threadpoo I. defau It.al low Maximums izeToDi vergeFrom Cores ize ：该属性允许maximumSize 的配置生效。那么该值可以等于或高于 coreSize。
     设置 coreSize 小于 maximum Size 会创建一个线程池， 该线程池可以支持 maximumSize 并发，但在相对 不活动期间将向系统返回线程。 默认值为 false。
• hystrix. thread pool. default. metrics .rollings tats. tim巳InMilliseconds ： 设置滚动时间窗的 时间 ，单位为毫秒， 默认值是 10 000。
• hystrix.threadpool.d巳fault.metrics.rollingStats.numBuckets ：设置滚动时间窗划分桶的 数量，默认值为 10。
*/
