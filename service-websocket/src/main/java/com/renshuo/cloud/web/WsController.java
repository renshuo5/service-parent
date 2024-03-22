package com.renshuo.cloud.web;

import com.renshuo.cloud.ws.WebSocketSever;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Lenovo on 2023/10/16.
 */
@RestController
@Slf4j
public class WsController {


    @Resource
    private WebSocketSever webSocket;

    /**
     * 发个用户id的消息
     *
     * @param userId
     * @param message
     */
    @PostMapping("/v1/ws/send")
    public void sendToUser(String userId, String message) {
        log.info("开始发送ws啦");
        webSocket.sendMessageByUser(userId, message);
    }


    /**
     * 发给所有已经链接ws的用户消息
     *
     * @param message
     */
    @PostMapping("/v1/ws/sendAll")
    public void sendAll(String message) {
        log.info("开始发送ws啦");
        webSocket.sendAllMessage(message);
    }

}
