package com.renshuo.cloud.enums;

/** 
* @description: 用户登录授权报错提示信息
*
* @author: renshuo 
* @date: 2023/8/14 
*/
public enum UserStatusCodeEnum {

    ACCOUNT_NOT_EXIST("账号不存在"),
    LOGIN_PASSWORD_ERROR("登录密码错误"),
    ACCOUNT_EXPIRED("账号过期"),
    ACCOUNT_LOCKED("账号锁定"),
    ACCOUNT_CREDENTIAL_EXPIRED("账号证书过期"),
    ACCOUNT_DISABLE("账号禁用"),
    USER_UNAUTHORIZED("用户未授权"),
    NOT_AUTHORIZATION("认证信息为空"),
    PERMISSION_DENIED("权限不足");

    private String msg;

    UserStatusCodeEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }


}
