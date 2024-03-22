package com.renshuo.cloud.controller;

import com.renshuo.cloud.feign.HelloFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
* @description: 访问feign
*
* @author: renshuo 
* @date: 2020/12/2 
*/
@RestController
public class HelloFeignController {


    @Autowired
    private HelloFeignClient helloFeignClient;

    @GetMapping(value="/search/github")
    public String helloGitHub(@RequestParam("str")String queryStr){
        return helloFeignClient.searchRepo(queryStr);
    }
}
