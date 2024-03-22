package com.renshuo.cloud.wechat.controller;


import com.renshuo.cloud.bean.TokenBean;
import com.renshuo.cloud.dispatcher.EventDispatcher;
import com.renshuo.cloud.dispatcher.MsgDispatcher;
import com.renshuo.cloud.util.MessageUtil;
import com.renshuo.cloud.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @description: 微信验证
 * @author: renshuo
 * @date: 2021/1/28
 */
@RestController
@RequestMapping("/wechat")
public class WechatSecurityController {

    private static Logger logger = LoggerFactory.getLogger(WechatSecurityController.class);

    @GetMapping("/security")
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) {
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                logger.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @PostMapping("/security")
    // post方法用于接收微信服务端消息
    public void DoPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("这是post方法！");
        try {
            Map<String, String> map = MessageUtil.parseXml(request);
            System.out.println("content=============================" + map.get("Content"));
            String msgtype = map.get("MsgType");
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)) {

                String msgrsp=EventDispatcher.processEvent(map); //进入事件处理
                PrintWriter out = response.getWriter();
                out.print(msgrsp);
                out.close();
            } else {

                String msgrsp=MsgDispatcher.processMessage(map); //进入消息处理
                PrintWriter out = response.getWriter();
                out.print(msgrsp);
                out.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @GetMapping("/getToken")
    public String getToken(){

        TokenBean tb = TokenBean.getInstance();
        return tb.getToken();
    }
}
