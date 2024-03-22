package com.renshuo.cloud.sys.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @description: hello
*
* @author: renshuo
* @date: 2020/12/4
*/
@RestController
public class HelloController {

    @Value("${server.servlet.context-path}")
    private String path ;

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public String hello(){
        return "path:"+path+"port:"+port;
    }
}
