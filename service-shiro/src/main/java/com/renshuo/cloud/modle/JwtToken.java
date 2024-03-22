package com.renshuo.cloud.modle;

import org.apache.shiro.authc.AuthenticationToken;

/** 
* @description: jwtToken使用
*
* @author: renshuo 
* @date: 2023/8/2 
*/
public class JwtToken implements AuthenticationToken {
    /**
     * 接口权限token类型
     */
    public static final String API_TYPE = "api";
    /**
     * 用户权限token类型
     */
    public static final String USER_TYPE = "user";

    private String tokenType;

    private String token;

    public JwtToken(String tokenType, String token) {
        this.tokenType = tokenType;
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
