package com.renshuo.cloud.user.controller;

import com.renshuo.cloud.common.ResultMsg;
import com.renshuo.cloud.reqbean.PagerInfo;
import com.renshuo.cloud.reqbean.PagerResult;
import com.renshuo.cloud.user.domain.User;
import com.renshuo.cloud.user.model.EmailModel;
import com.renshuo.cloud.user.service.EmailService;
import com.renshuo.cloud.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 用户模块的用户控制层
 * @author: renshuo
 * @date: 2020/12/4
 */
@RestController
@RequestMapping("user")
@Api(value = "用户管理", description = "用户管理", tags = {"用户管理"})
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.sendto}")
    private String sendto;
    @Value("${spring.mail.username}")
    private String form;

    @PostMapping("/list")
    @ApiOperation(value = "多条件列表查询", notes = "多条件列表查询")
    public ResultMsg list(@RequestBody PagerInfo pagerInfo) {
        return ResultMsg.success(userService.list(pagerInfo));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "id查询", notes = "id查询")
    public ResultMsg get(@PathVariable String id) {
        return ResultMsg.success(userService.get(id));
    }

    @PostMapping("/exportPage")
    @ApiOperation(value = "多条件批次导出", notes = "多条件批次导出")
    public void exportPage(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        userService.exportPage(response, pagerInfo);
    }

    @PostMapping("/exportAll")
    @ApiOperation(value = "多条件全部导出", notes = "多条件全部导出")
    public void exportAll(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        userService.exportAll(response, pagerInfo);
    }


    @PostMapping("/sendMessage")
    @ApiOperation(value = "通过页面发送ws消息", notes = "通过页面给所有链接的用户发送ws消息")
    public void sendMessage(@RequestBody String message) {
        userService.sendMessage(message);
    }

    @PostMapping("/sendEmail")
    @ApiOperation(value = "通过页面发送email消息", notes = "通过页面发送email消息")
    public void sendEmail(@RequestBody String message) {
        EmailModel e = new EmailModel();
        List<String> sendList = new ArrayList<>();
        sendList.add(sendto);
        e.setReceiverList(sendList);
        e.setSender(form);
        e.setSubject("测试");
//        emailService.sendSimpleEmail(e);
        emailService.sendMimeEmail(e);
    }
}
