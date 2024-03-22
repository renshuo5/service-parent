package com.renshuo.cloud.handler;

import com.alibaba.fastjson.JSONObject;
import com.renshuo.cloud.model.LoginUser;
import com.renshuo.cloud.util.ResultMsg;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @description: 退出登录后执行的类方法
 * @author: renshuo
 * @date: 2023/8/21
 */
@Component
public class DefaultServerLogoutSuccessHandler implements ServerLogoutSuccessHandler {

    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        return Mono.defer(() -> Mono.just(exchange.getExchange().getResponse()))
                .flatMap(response -> {
                    response.setStatusCode(HttpStatus.OK);
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

                    DataBufferFactory dataBufferFactory = response.bufferFactory();
                    DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(ResultMsg.success("退出登录成功")).getBytes());

                    return response.writeWith(Mono.just(dataBuffer));
                });
    }
}
