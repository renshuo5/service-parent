package com.renshuo.cloud.controller;

import com.renshuo.cloud.common.ResultMsg;
import com.renshuo.cloud.domain.User;
import com.renshuo.cloud.service.LoginService;
import com.renshuo.cloud.service.UserService;
import com.renshuo.cloud.util.UtilHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


/**
* @description:
*
* @author: renshuo
* @date: 2023/8/10
*/
@RestController
@Api(value = "测试", description = "测试1", tags = {"测试2"})
public class HelloController {



    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginServcie;


    @ApiOperation(value = "你好")
    @GetMapping("/hello")
    public ResultMsg hello() {

        return ResultMsg.success("hello word");
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResultMsg login() {
        User user = new User("renshuo", "123456");

        return loginServcie.login(user);
    }

    @ApiOperation(value = "登出")
    @PostMapping("/user/logout")
    public ResultMsg logout() {
        return loginServcie.logout();
    }

    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    public ResultMsg register(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 对明文密码加密
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        String uuid = UtilHelper.getUUID();
        user.setSalt("rs");
        user.setLocked(0);
        user.setId(uuid);
        int insert = userService.insert(user);
        return insert>0?ResultMsg.success("添加成功"):ResultMsg.fail("添加失败");
    }

    @ApiOperation(value = "权限校验方法测试")
    @PostMapping("/per")
    @PreAuthorize("hasAuthority('pre')")
    public ResultMsg per() {

        return ResultMsg.success("hello word");
    }
    @ApiOperation(value = "权限校验方法测试")
    @PostMapping("/insert")
    @PreAuthorize("hasAuthority('user:insert')")
    public ResultMsg insert() {

        return ResultMsg.success("hello word");
    }
}
