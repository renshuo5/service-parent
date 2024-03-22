package com.renshuo.cloud.service;

import com.alibaba.fastjson.JSON;
import com.renshuo.cloud.common.ResultMsg;
import com.renshuo.cloud.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 
* @description: 访问被拒绝的处理程序 
*
* @author: renshuo 
* @date: 2023/8/9 
*/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        String json = JSON.toJSONString(ResultMsg.fail("权限不足"));
        WebUtils.renderString(response, json);


    }
}
