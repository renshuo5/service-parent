package com.renshuo.cloud.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/** 
* @description:
 * 确定是否提供了AuthenticationToken凭证与系统中存储的相应帐户的凭证相匹配。
 * 如果不自定义，会用默认的验证方式，有可能会导致 Submitted credentials for token [com.example.shiro.configuration.JwtToken@30d592d5] did not match the expected credentials.
 *
 * UserJwtFilter ————> MyRealm ————> JwtCredentialsMatcher 
*
* @author: renshuo 
* @date: 2023/8/2 
*/
@Slf4j
public class JwtCredentialsMatcher  extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return super.equals(token, info.getCredentials());
    }
}
