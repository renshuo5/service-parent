package com.renshuo.cloud.handler;

import com.alibaba.fastjson.JSONObject;
import com.renshuo.cloud.enums.UserStatusCodeEnum;
import com.renshuo.cloud.util.ResultMsg;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class DefaultAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {
    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange()
                .getResponse()).flatMap(response -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            ResultMsg result = new ResultMsg();
            // 账号不存在
            if (exception instanceof UsernameNotFoundException) {
                result = ResultMsg.fail(UserStatusCodeEnum.ACCOUNT_NOT_EXIST.getMsg());
                // 用户名或密码错误
            } else if (exception instanceof BadCredentialsException) {
                result = ResultMsg.fail(UserStatusCodeEnum.LOGIN_PASSWORD_ERROR.getMsg());
                // 账号已过期
            } else if (exception instanceof AccountExpiredException) {
                result = ResultMsg.fail(UserStatusCodeEnum.ACCOUNT_EXPIRED.getMsg());
                // 账号已被锁定
            } else if (exception instanceof LockedException) {
                result = ResultMsg.fail(UserStatusCodeEnum.ACCOUNT_LOCKED.getMsg());
                // 用户凭证已失效
            } else if (exception instanceof CredentialsExpiredException) {
                result = ResultMsg.fail(UserStatusCodeEnum.ACCOUNT_CREDENTIAL_EXPIRED.getMsg());
                // 账号已被禁用
            } else if (exception instanceof DisabledException) {
                result = ResultMsg.fail(UserStatusCodeEnum.ACCOUNT_DISABLE.getMsg());
            }
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

            DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(result).getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }));
    }
}
