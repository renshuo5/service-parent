package com.renshuo.cloud.config;

import com.renshuo.cloud.enums.UserStatusCodeEnum;
import com.renshuo.cloud.model.LoginUser;
import com.renshuo.cloud.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @description:
 * @author: renshuo
 * @date: 2023/8/15
 */
@Component
@Qualifier("tokenAuthenticationManager")
public class TokenAuthenticationManager implements ReactiveAuthenticationManager {

    @Value("${jwtSecret:salt}")
    private String secret;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(auth -> JwtUtil.getTokenInfo(auth.getPrincipal().toString(), secret))
                .map(tokenInfo -> {
                    //根据用户id获取redis里面用户信息
                    String userId = tokenInfo.getUserId();
                    long now = tokenInfo.getCreateTime();
                    String redisKey = "login:" + userId+":"+now;
                    LoginUser loginUser = redisCache.getCacheObject(redisKey);
                    if (loginUser != null) {
                        loginUser.setLoginTime(now);
                        return new UsernamePasswordAuthenticationToken(
                                loginUser,
                                null,
                                loginUser.getAuthorities()
                        );
                    } else {
                        throw new CredentialsExpiredException(UserStatusCodeEnum.ACCOUNT_CREDENTIAL_EXPIRED.getMsg());
                    }
                });
    }
}
