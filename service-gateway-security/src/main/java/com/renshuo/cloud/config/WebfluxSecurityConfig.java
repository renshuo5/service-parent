package com.renshuo.cloud.config;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.renshuo.cloud.handler.*;
import com.renshuo.cloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerFormLoginAuthenticationConverter;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.LinkedList;

/**
 * @description:
 * @author: renshuo
 * @date: 2023/8/11
 */
@Configuration
@EnableWebFluxSecurity
@Slf4j
public class WebfluxSecurityConfig {

    @Resource
    private DefaultAuthorizationManager defaultAuthorizationManager;

    @Resource
    private UserService userService;

    @Resource
    private DefaultAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

    @Resource
    private DefaultAuthenticationFailureHandler defaultAuthenticationFailureHandler;

    @Resource
    private DefaultSecurityContextRepository defaultSecurityContextRepository;

    @Resource
    private DefaultAuthenticationEntryPoint defaultAuthenticationEntryPoint;

    @Resource
    private DefaultAccessDeniedHandler defaultAccessDeniedHandler;

    @Resource
    public AuthenticationWebFilter authenticationWebFilter;

    @Resource
    public DefaultServerLogoutHandler defaultServerLogoutHandler;

    @Resource
    public DefaultServerLogoutSuccessHandler defaultServerLogoutSuccessHandler;

    /**
     * 自定义过滤权限
     */
    @Value("${security.noFilter:''}")
    private String noFilter;

    /**
     * 自定义登录后即可访问的url
     */
    @Value("${security.authFilter:/user/hello}")
    private String authFilter;

    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */

    String[] swaggerUrl = new String[]{
            "**/swagger-ui/**",
            "**/swagger-resources/**",
            "**/v2/api-docs",
            "**/v3/api-docs",
            "**/webjars/**",
            "**/doc.html",  // 在原有的swagger3放行基础上加上 /doc.html
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {

        httpSecurity
                // 登录认证处理
                .authenticationManager(reactiveAuthenticationManager())
                .securityContextRepository(defaultSecurityContextRepository)
                .authorizeExchange()
                .pathMatchers("/public/**").permitAll()
                .pathMatchers("/login").permitAll()
                .pathMatchers(swaggerUrl).permitAll()
//                .pathMatchers(noFilter).permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                //.anyExchange().authenticated()
                .pathMatchers(authFilter).authenticated()
                .pathMatchers("/logout").authenticated()
                .anyExchange().access(defaultAuthorizationManager)
                .and()

                .formLogin()
                .disable()
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.FORM_LOGIN)
//                .formLogin()
//                // 自定义处理
//                .authenticationSuccessHandler(defaultAuthenticationSuccessHandler)
//                .authenticationFailureHandler(defaultAuthenticationFailureHandler)
//                .and()

                .logout().logoutHandler(defaultServerLogoutHandler)
                .logoutSuccessHandler(defaultServerLogoutSuccessHandler)
                .and()
                // 自定义处理
                .exceptionHandling()
                .authenticationEntryPoint(defaultAuthenticationEntryPoint)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(defaultAccessDeniedHandler)
                .and()
                .csrf().disable()

        ;
        return httpSecurity.build();
    }


    /**
     * 自定义异常处理[@@]注册Bean时依赖的Bean，会从容器中直接获取，所以直接注入即可
     *
     * @return ErrorWebExceptionHandler
     */
    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler() {
        JsonExceptionHandler jsonExceptionHandler = new JsonExceptionHandler();
        return jsonExceptionHandler;
    }

    @Bean
    public ServerFormLoginAuthenticationConverter formLoginAuthenticationConverter() {
        return new CustomServerFormLoginAuthenticationConverter();
    }

    /**
     * BCrypt密码编码
     */
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 注册用户信息验证管理器，可按需求添加多个按顺序执行
     */
    @Bean
    @Primary
    ReactiveAuthenticationManager reactiveAuthenticationManager() {
        LinkedList<ReactiveAuthenticationManager> managers = new LinkedList<>();

        managers.add(authentication -> {
            // 其他登陆方式 (比如手机号验证码登陆) 可在此设置不得抛出异常或者 Mono.error
            return Mono.empty();
        });
        // 必须放最后不然会优先使用用户名密码校验但是用户名密码不对时此 AuthenticationManager 会调用 Mono.error 造成后面的 AuthenticationManager 不生效
        managers.add(new UserDetailsRepositoryReactiveAuthenticationManager(userService));
//        managers.add(tokenAuthenticationManager);
        return new DelegatingReactiveAuthenticationManager(managers);
    }

    //    @Bean
//    @Primary
//    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
//        return new UserDetailsRepositoryReactiveAuthenticationManager(userService);
//    }
    @Bean
    public AuthenticationWebFilter authenticationWebFilter(ReactiveAuthenticationManager authenticationManager) {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(authenticationManager);
        // 重要：创建路径匹配规则，仅匹配/login路径
        ServerWebExchangeMatcher pathMatcher = new PathPatternParserServerWebExchangeMatcher("/login");
        authenticationWebFilter.setRequiresAuthenticationMatcher(pathMatcher);
        //登录失败和登录成功进行的处理类
        authenticationWebFilter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);
        authenticationWebFilter.setAuthenticationSuccessHandler(defaultAuthenticationSuccessHandler);
        //进行自定义转换-数据类型gateway获取body数据流信息
        authenticationWebFilter.setServerAuthenticationConverter(formLoginAuthenticationConverter());
        return authenticationWebFilter;
    }

}
