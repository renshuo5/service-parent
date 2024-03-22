package com.renshuo.cloud.config;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @description: 用户登录认证赋值
 * @author: renshuo
 * @date: 2023/8/14
 */
@Component
@Slf4j
public class DefaultSecurityContextRepository implements ServerSecurityContextRepository {


    public final static String TOKEN_HEADER = "Authorization";

    public final static String BEARER = "Bearer ";

    @Resource
    private TokenAuthenticationManager tokenAuthenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {

        // 身份验证逻辑
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst(TOKEN_HEADER);
        if (StringUtils.isNotBlank(authorization)) {
            String token = authorization.substring(BEARER.length());
            if (StringUtils.isNotBlank(token)) {
                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(token, null);
                Mono<Authentication> authenticate = tokenAuthenticationManager.authenticate(user);
                // 身份验证成功
                return authenticate.map(SecurityContextImpl::new);
            }
        }
        return Mono.empty();
        // 处理未经身份验证的请求
//        SecurityContextImpl emptyContext = new SecurityContextImpl();
//        emptyContext.setAuthentication(createAnonymousAuthentication());
//        return Mono.just(emptyContext);
    }

    private Authentication createAnonymousAuthentication() {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
        return new AnonymousAuthenticationToken("anonymous", "anonymous", authorities);
    }
}
