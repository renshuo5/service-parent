package com.renshuo.cloud.service;

import com.alibaba.fastjson.JSON;
import com.renshuo.cloud.common.ResultMsg;
import com.renshuo.cloud.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 
* @description:
*
* @author: renshuo 
* @date: 2023/8/9 
*/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String json = JSON.toJSONString(ResultMsg.fail("认证失败请重新登录"));
        WebUtils.renderString(response,json);
    }
}
