package com.renshuo.cloud.user.controller;

import com.renshuo.cloud.user.service.UserService;
import com.renshuo.cloud.util.NetworkInfoUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: hello
 * @author: renshuo
 * @date: 2020/12/4
 */
@RestController
public class HelloController {

    @Value("${server.servlet.context-path}")
    private String path;

    @Value("${server.port}")
    private String port;


    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        String networkInfo = NetworkInfoUtil.getNetworkInfo();

        return "hello---path:" + path + ",port:" + port+",mac address:"+networkInfo;
    }

    @PostMapping("/access")
    public String access() {
        return "access---path:" + path + ",port:" + port;
    }

}
