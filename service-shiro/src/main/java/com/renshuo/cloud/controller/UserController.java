package com.renshuo.cloud.controller;

import com.renshuo.cloud.domain.User;
import com.renshuo.cloud.modle.ResultMsg;
import com.renshuo.cloud.service.UserService;
import com.renshuo.cloud.util.JwtUtil;
import com.renshuo.cloud.util.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lenovo on 2023/8/1.
 */
@RestController
public class UserController {


    @Value("${jwtSalt:salt}")
    private String jwtSalt;


    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {

        String username = "admin";
        String password = "123456";
        UsernamePasswordToken up = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(up);

        return "success";
    }


    @RequestMapping("/loginJwt")
    public ResultMsg login2() throws Exception {

        String username = "admin";
        String password = "admin";
        //todo 相当于前端直接传入md5密码
        String htmlPass = SecurityUtil.generateMD5Password(password);

//        UsernamePasswordToken up = new UsernamePasswordToken(username, password);
//        Subject currentUser = SecurityUtils.getSubject();
//        currentUser.login(up);

//        User user =(User)currentUser.getPrincipal();

        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException();//账户不存在
        }
        if (user.getLocked() == 1) {
            throw new LockedAccountException();
        }

        //校验密码 todo 应该直接传入加密后的密码，防止拦截
        //校验方式 1、明文密码+salt 进行加密
        String pass = SecurityUtil.generateSaltedMD5Password(htmlPass, user.getSalt());

        if(!pass.equals(user.getPassword())){
            throw new IncorrectCredentialsException();
        }

        String token = JwtUtil.createJwt(user, jwtSalt, -1);
        return ResultMsg.success(token);
    }

    @RequiresPermissions("user:list")
    @RequestMapping("/users")
    public String list() {

        return "success";
    }

    @RequiresPermissions("user:insert")
    @RequestMapping("/user")
    public String insert() {

        return "success";
    }

}
