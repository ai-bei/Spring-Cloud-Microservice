package com.iotknowyou.SpringRestTemplate.Annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier
public @interface MyLoadBalanced {

    /*
        为了进一步了解，@LoadBalanced 如何实现 RestTemplate 和 Eureka 进行结合的

            自定义 @MyLoadBalanced 去实现 @LoadBalanced 的功能
    */
}
