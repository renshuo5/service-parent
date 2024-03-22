package com.renshuo.cloud.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.renshuo.cloud.common.exception.BusinessException;
import com.renshuo.cloud.common.model.ResultMsg;
import com.renshuo.cloud.config.RedisCache;
import com.renshuo.cloud.constant.Version;
import com.renshuo.cloud.sys.service.LoginService;
import com.renshuo.cloud.user.model.LoginModel;
import com.renshuo.cloud.util.CommonUtil;
import com.renshuo.cloud.util.CookieUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lenovo on 2024/3/15.
 */
@RestController
@Slf4j
@Api(value = "系统管理-登录", description = "提供用户登入、用户登出和获取CSRF的接口", tags = {"系统管理-登录"})
@RequestMapping(value = "/" + Version.VERSION_V1)
public class LoginController {


    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisCache redisCache;

    @ApiOperation(value = "获取CSRF", notes = "用于获取csrf值，任何人都可以访问该接,服务器在cookie中写入CSRF")
    @GetMapping(value = "/first-login")
    public ResultMsg firstLogin(HttpServletResponse response) {

        String csrf = "renshuo-test-csrf";
        CookieUtils.addCookie("CSRF", csrf);
        response.setHeader("x-api-csrf", csrf);
        return ResultMsg.success("获取csrf值成功");
    }

    @ApiOperation(value = "用户登出", notes = "用于用户登出，登出后CSRF和TOKEN都会被清除")
    @GetMapping(value = "/logout")
    public ResultMsg logout() {
        loginService.logout();
        return ResultMsg.success("登出成功");
    }

    /**
     * 用户登录的方法
     *
     * @param loginModel
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "用户登录成功之后，返回给前端一个token")
    @PostMapping("/login")
    public ResultMsg login(@RequestBody LoginModel loginModel) {
        ResultMsg login = loginService.login(loginModel);
        return login;
    }

    @ApiOperation(value = "获取个人信息",notes = "获取当前用户的详细信息")
    @GetMapping(value = "/me")
    public ResultMsg me(){

        Map<String,Object> userModel = loginService.me();
        return ResultMsg.success(userModel);
    }

    /**
     * 根据id获取对象
     *
     * @return
     */
    @ApiOperation(value = "获取初始化菜单、logo、首页设置", notes = "获取用户菜单")
    @GetMapping(value = "/init")
    public ResultMsg init() {
        ResultMsg init = loginService.init();
        return init;
    }

    @GetMapping("/randCode")
    public void getCode(HttpServletResponse response) {
        // 随机生成 4 位验证码
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
            // 定义图片的显示大小
            //验证码工具类
            LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 40);
            // 调用父类的 setGenerator() 方法，设置验证码的类型
            lineCaptcha.setGenerator(randomGenerator);
            // 打印日志
            String code = lineCaptcha.getCode();
            log.info("生成的验证码:{}", code);
            String csrf = CommonUtil.getCsrf();
            redisCache.setCacheObject("randCode_" + csrf, code, 5, TimeUnit.MINUTES);
            // 输出到页面
            lineCaptcha.write(response.getOutputStream());
            // 关闭流
            response.getOutputStream().close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("获取验证码失败");
        }
    }


}
