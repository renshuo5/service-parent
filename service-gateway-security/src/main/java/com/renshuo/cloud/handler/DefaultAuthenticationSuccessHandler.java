package com.renshuo.cloud.handler;

import com.alibaba.fastjson.JSONObject;
import com.renshuo.cloud.config.RedisCache;
import com.renshuo.cloud.domain.User;
import com.renshuo.cloud.model.LoginUser;
import com.renshuo.cloud.model.TokenInfo;
import com.renshuo.cloud.util.JwtUtil;
import com.renshuo.cloud.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 认证成功后执行方法
 * @author: renshuo
 * @date: 2023/8/14
 */
@Component
public class DefaultAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    @Autowired
    private RedisCache redisCache;

    @Value("${jwtSecret:salt}")
    private String secret;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange().getResponse()).flatMap(response -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            // 生成JWT token
            LoginUser lu = (LoginUser) authentication.getPrincipal();
            String userId = lu.getUser().getId().toString();
            long now = System.currentTimeMillis();
            String redisKey = "login:" + userId+":"+now;
            //authenticate存入redis
            redisCache.setCacheObject(redisKey, lu);
            String jwt = null;
            try {

                //设置token
                TokenInfo token = new TokenInfo();
                token.setUserId(userId);
                token.setCreateTime(now);
                token.setUsername(lu.getUser().getUsername());

                jwt = JwtUtil.createTokenInfo(token, secret, -1);
//                jwt = JwtUtil.createJwt(userId, secret, -1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("token", jwt);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            // 设置返回类型为JSON
//            response.setHeader("Content-Type", "application/json");
            DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(ResultMsg.success(map)).getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }));
    }
}
