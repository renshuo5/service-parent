package com.renshuo.cloud.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 访问websocket服务
 * @author: renshuo
 * @date: 2023/10/16
 */
@FeignClient("service-ws")
public interface WsServiceClient {


    @PostMapping("/api/v1/ws/sendToUser")
    void sendToUser(@RequestParam("userId") String userId, @RequestParam("message") String message);


    @PostMapping("/api/v1/ws/sendAll")
    void sendToAll(@RequestParam("message") String message);

}
