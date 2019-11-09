package com.iotknowyou.service;

import com.iotknowyou.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


/*
    Feign 的继承特性可以让服务的接口定义单独的抽出来，作为公共的依赖，以方便使用
*/


@FeignClient("feign-inherit-provide")
public interface UserRemoteClient {

    @GetMapping("/user/name")
    String getName();

    @GetMapping("/user/info")
    String getUserInfo(@RequestParam("name")String name);

    @GetMapping("/user/detail")
    String getUserDetail(@RequestParam Map<String, Object> param);

    @PostMapping("/user/add")
    String addUser(@RequestBody User user);

}
