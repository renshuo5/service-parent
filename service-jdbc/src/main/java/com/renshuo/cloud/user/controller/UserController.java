package com.renshuo.cloud.user.controller;

import com.github.pagehelper.PageInfo;
import com.renshuo.cloud.reqbean.PagerInfo;
import com.renshuo.cloud.reqbean.PagerResult;
import com.renshuo.cloud.user.domain.User;
import com.renshuo.cloud.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** 
* @description: 用户模块的用户控制层
*
* @author: renshuo 
* @date: 2020/12/4 
*/
@RestController
@Api(value = "用户管理", description = "用户管理d", tags = {"用户管理t"})
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ApiOperation(value = "多条件列表查询", notes = "多条件列表查询")
    public PageInfo list(@RequestBody PagerInfo pagerInfo){
        return userService.list(pagerInfo);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "id查询", notes = "id查询")
    public User get(@PathVariable String id){
        return userService.findById(id);
    }
}
