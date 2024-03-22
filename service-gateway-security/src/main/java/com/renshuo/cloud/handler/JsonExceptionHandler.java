package com.renshuo.cloud.handler;

import com.alibaba.fastjson.JSONObject;
import com.renshuo.cloud.enums.UserStatusCodeEnum;
import com.renshuo.cloud.util.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.ConnectException;
import java.nio.charset.Charset;

/**
 * @description: 统一异常处理类
 * @author: renshuo
 * @date: 2023/8/21
 */
@Slf4j
public class JsonExceptionHandler implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable exception) {

        return Mono.defer(() -> Mono.just(serverWebExchange.getResponse())).flatMap(response -> {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            DataBufferFactory dataBufferFactory = response.bufferFactory();

            ResultMsg result;
            if (exception instanceof NotFoundException) {
                result = ResultMsg.fail("没有找到资源");
            } else if (exception instanceof ConnectException) {
                result = ResultMsg.fail("系统正在维护中，请稍后再访问");
            } else if (exception instanceof UsernameNotFoundException) {
                result = ResultMsg.fail(UserStatusCodeEnum.ACCOUNT_NOT_EXIST.getMsg());
            } else if (exception instanceof BadCredentialsException) {
                // 用户名或密码错误
                result = ResultMsg.fail(UserStatusCodeEnum.LOGIN_PASSWORD_ERROR.getMsg());
            } else if (exception instanceof AccountExpiredException) {
                // 账号已过期
                result = ResultMsg.fail(UserStatusCodeEnum.ACCOUNT_EXPIRED.getMsg());
            } else if (exception instanceof LockedException) {
                // 账号已被锁定
                result = ResultMsg.fail(UserStatusCodeEnum.ACCOUNT_LOCKED.getMsg());
            } else if (exception instanceof CredentialsExpiredException) {
                // 用户凭证已失效
                result = ResultMsg.fail(UserStatusCodeEnum.ACCOUNT_CREDENTIAL_EXPIRED.getMsg());
            } else if (exception instanceof DisabledException) {
                // 账号已被禁用
                result = ResultMsg.fail(UserStatusCodeEnum.ACCOUNT_DISABLE.getMsg());
            } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
                //认证信息为空
                result = ResultMsg.fail(UserStatusCodeEnum.NOT_AUTHORIZATION.getMsg());
            } else {
                result = ResultMsg.fail("系统内部出现异常，正在解决中，请稍后再访问");
            }
            String res = JSONObject.toJSONString(result);
            DataBuffer buffer = dataBufferFactory.wrap(res.getBytes(
                    Charset.defaultCharset()));
            log.error("error:", exception);
            return response.writeWith(Mono.just(buffer));
        });


    }
}
