package com.renshuo.cloud.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("你好")
public class HelloController {

    @Value("${server.servlet.context-path}")
    private String path ;

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    @ApiOperation("你也好")
    public String hello(){
        return "path:"+path+",port:"+port;
    }
}
