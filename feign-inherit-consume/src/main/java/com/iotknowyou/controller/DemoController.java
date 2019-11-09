package com.iotknowyou.controller;

import com.iotknowyou.entity.User;
import com.iotknowyou.service.UserRemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {

    @Autowired
    private UserRemoteClient userRemoteClient;

    @GetMapping("/call")
    public String callHello() {
        String result = userRemoteClient.getName();
        System.out.println("getName调用结果：" + result);

        result = userRemoteClient.getUserInfo("LiuRongHua1");
        System.out.println("getUserInfo调用结果：" + result);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", "LiuRongHua2");
        result = userRemoteClient.getUserDetail(param);
        System.out.println("getUserDetail调用结果：" + result);

        User user = new User();
        user.setName("LiuRongHua3");
        result = userRemoteClient.addUser(user);
        System.out.println("addUser调用结果：" + result);
        return result;
    }


}

