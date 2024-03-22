package com.renshuo.cloud.shiro.exception;

import com.renshuo.cloud.modle.ResultErrorMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Lenovo on 2023/8/1.
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = ShiroException.class)
    @ResponseBody
    public ResultErrorMsg doShiroException(ShiroException e) {
        ResultErrorMsg r = new ResultErrorMsg();
        r.setState(0);
        if (e instanceof UnknownAccountException) {
            r.setDetailException("用户名不存在");
        } else if (e instanceof IncorrectCredentialsException) {
            r.setDetailException("密码不正确");
        } else if (e instanceof LockedAccountException) {
            r.setDetailException("账户被锁定");
        } else if (e instanceof AuthorizationException) {
            r.setDetailException("没有权限");
        } else {
            r.setDetailException("认证或授权失败");
        }
        return r;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultErrorMsg exception(Exception e) {
        log.error("error:",e);
        ResultErrorMsg r = new ResultErrorMsg();
        r.setState(0);
        r.setErrorCode("系统出现异常");
        r.setDetailException(e.getMessage());

        return r;
    }
}
