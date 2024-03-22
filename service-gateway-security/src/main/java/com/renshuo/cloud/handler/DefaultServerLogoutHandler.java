package com.renshuo.cloud.handler;

import com.renshuo.cloud.config.RedisCache;
import com.renshuo.cloud.enums.UserStatusCodeEnum;
import com.renshuo.cloud.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @description: 退出登录的执行器
 * @author: renshuo
 * @date: 2023/8/16
 */
@Component
public class DefaultServerLogoutHandler implements ServerLogoutHandler {

    @Autowired
    private RedisCache redisCache;

    @Value("${jwtSecret:salt}")
    private String secret;

    @Override
    public Mono<Void> logout(WebFilterExchange exchange, Authentication authentication) {

        /*
        在 Spring Security 中，LogoutHandler 的实现类中无法直接修改响应头部的原因是为了确保安全性和一致性。

    当用户进行退出登录操作时，LogoutHandler 负责处理注销的逻辑，包括清除用户的认证凭证、清除相关的会话信息等。在这个过程中，Spring Security 需要保证注销操作的安全性和可靠性，以防止潜在的安全漏洞。

    如果允许 LogoutHandler 直接修改响应头部，可能会导致潜在的安全问题。例如，一个恶意的实现可以修改响应头部来欺骗用户或窃取用户信息。

    另外，Spring Security 采用了一种链式处理的机制来处理注销操作，不仅包括 LogoutHandler，还包括其他一些处理器，例如 LogoutSuccessHandler。这些处理器按照一定的顺序执行，确保注销操作的一致性和可预测性。

    如果你想在注销操作中修改响应头部，可以考虑使用 LogoutSuccessHandler 接口的实现类来处理，该接口提供了一个 onLogoutSuccess() 方法，可以在注销成功后对响应进行自定义处理，包括修改响应头部。
    需要注意的是，LogoutSuccessHandler 的实现类中也是只读的，所以你仍然需要使用 ServerHttpResponseDecorator 类来包装原始的响应对象并修改响应头部
         */
        return Mono.defer(() -> Mono.just(exchange.getExchange().getResponse()))
                .flatMap(response -> {
                    if (null == authentication.getPrincipal()) {
                        throw new AuthenticationCredentialsNotFoundException(UserStatusCodeEnum.NOT_AUTHORIZATION.getMsg());
                    }
                    if("anonymous".equals(authentication.getPrincipal())){
                        throw new AuthenticationCredentialsNotFoundException(UserStatusCodeEnum.NOT_AUTHORIZATION.getMsg());
                    }
                    LoginUser loginUser = (LoginUser) authentication.getPrincipal();
                    long now = loginUser.getLoginTime();
                    String userid = loginUser.getUser().getId();
                    redisCache.deleteObject("login:" + userid + ":" + now);

                    return Mono.empty();
                });
    }
}
