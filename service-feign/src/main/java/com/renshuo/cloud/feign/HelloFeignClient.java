package com.renshuo.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** 
* @description: github访问
*
* @author: renshuo 
* @date: 2020/12/2 
*/
@FeignClient(name="github-client",url="https://api.github.com/")
public interface HelloFeignClient {

    @RequestMapping(value = "/search/repositories",method = RequestMethod.GET)
    String searchRepo(@RequestParam("q")String queryStr);

}
