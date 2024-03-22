package com.renshuo.cloud.filter;

import cn.hutool.http.Header;
import com.renshuo.cloud.modle.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lenovo on 2023/8/2.
 */
@Slf4j
public class UserJwtFilter extends BasicHttpAuthenticationFilter {

    @Lazy
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    /**
     * 执行登录认证方法
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            executeLogin(request, response);
        } else {
//            throw new UnauthorizedException("没有权限");
        }
        return true;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        String token = getAuthzHeader(request);
        return null != token;
    }

    /**
     * @param request
     * @param response
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(Header.AUTHORIZATION.getValue());
        Subject subject = getSubject(request, response);
        subject.login(new JwtToken(JwtToken.USER_TYPE, token));
        return true;
    }
}
