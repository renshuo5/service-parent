package com.renshuo.cloud.user.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.renshuo.cloud.common.model.ResultMsg;
import com.renshuo.cloud.config.RedisCache;
import com.renshuo.cloud.constant.Version;
import com.renshuo.cloud.user.model.LoginModel;
import com.renshuo.cloud.util.CookieUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
* @description: hello
*
* @author: renshuo
* @date: 2020/12/4
*/
@RestController
@Slf4j
@Api(value = "测试管理", description = "测试", tags = {"基础数据-测试"})
@RequestMapping(value = "/" + Version.VERSION_V1 + "/hello")
public class HelloController {

    @Value("${server.servlet.context-path}")
    private String path ;

    @Value("${server.port}")
    private String port;


    @Autowired
    private RedisCache redisCache;

    //验证码工具类
    private LineCaptcha lineCaptcha;

    @GetMapping("/hello")
    public String hello(){
        return "path:"+path+",port:"+port;
    }

    @ApiOperation(value = "获取CSRF",notes = "用于获取csrf值，任何人都可以访问该接,服务器在cookie中写入CSRF")
    @GetMapping(value = "/first-login")
    public ResultMsg firstLogin(HttpServletResponse response){

//        Map<String, String> map = realDataClient.findByUuid("314bc2b6-b9ce-4379-af2f-bec93cef741b", new String[]{"2000", "2502", "2503", "2308"}, DataReadMode.ALL);
//        ProtocolQueryModel rm = new ProtocolQueryModel();
//        rm.setUuid("dc87aebb-51f8-4138-982f-8258103cb4c8");
//        rm.setStartTime("2019-03-15 01:00:00");
//        rm.setEndTime("2019-04-15 01:00:00");
//        rm.setStart(0);
//        rm.setLimit(10);

//        PagerModel pm = protocolMessageClient.findProtocolMessage(rm);
//        log.info(new JsonSerializer().deep(true).serialize(pm));
//        Map<String, Map<String, String>> map = realDataClient.findByAllVehicle(new String[]{"2502","2503", "2000"});
//        redisKit.hget("1","1");
//        String authSql = DataAccessKit.getUserAuthSql("1", "sys_vehicle", "v");
        String csrf = "renshuo-test-csrf";
        CookieUtils.addCookie("CSRF", csrf);
        response.setHeader("x-api-csrf", csrf);
        return ResultMsg.success("获取csrf值成功");
    }

    /**
     * 用户登录的方法
     * @param loginModel
     * @return
     */
    @ApiOperation(value = "用户登录",notes = "用户登录成功之后，返回给前端一个token")
    @PostMapping("/login2")
    public ResultMsg userLogin(@RequestBody LoginModel loginModel){
        if(lineCaptcha==null){
            return ResultMsg.fail("验证码失效，请刷新页面");
        }
        String csrf = getCsrf();
        String code = redisCache.getCacheObject("randCode_" + csrf);
        log.info("验证码--"+code);
        Map<String, Object> resultMap = new HashMap<>();

        String token = "1231241231";
        resultMap.put("token", token);
        if (loginModel.getValidCode().equals(code)){
            //省略校验用户输入的其他信息....
            CookieUtils.getResponse().addHeader("Authorization","Bearer " + token);
            return ResultMsg.success(resultMap);
        }
        return ResultMsg.fail("登录失败");

    }


    @ApiOperation(value = "用户登录",notes = "用户登录成功之后，返回给前端一个token")
    @PostMapping("/login")
    public ResultMsg userLogin(String username,String password){
        System.out.println("验证码--"+lineCaptcha.getCode());


        String token = "1231241231";
        if (true){
            //省略校验用户输入的其他信息....
            return ResultMsg.success(token);
        }
        return ResultMsg.fail("登录失败");

    }

    @RequestMapping("/randCode")
    public void getCode(HttpServletResponse response) {
        // 随机生成 4 位验证码
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
            // 定义图片的显示大小
            lineCaptcha = CaptchaUtil.createLineCaptcha(100, 40);
            // 调用父类的 setGenerator() 方法，设置验证码的类型
            lineCaptcha.setGenerator(randomGenerator);
            // 打印日志
            String code = lineCaptcha.getCode();
            log.info("生成的验证码:{}", code);

            String csrf = getCsrf();
            redisCache.setCacheObject("randCode_"+csrf,code,5, TimeUnit.MINUTES);

            // 输出到页面
            lineCaptcha.write(response.getOutputStream());
            // 关闭流
            response.getOutputStream().close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取csrf值
     *
     * @return
     */
    private String getCsrf() {
        String csrf = "";
        try {
            csrf = CookieUtils.getCookie("CSRF");
        } catch (Exception e) {
            log.warn("获取不到CSRF值");
        }
        return StringUtils.isEmpty(csrf) ? "" : csrf;

    }




}
