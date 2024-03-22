package com.renshuo.cloud.shiro.realm;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.renshuo.cloud.domain.User;
import com.renshuo.cloud.modle.JwtToken;
import com.renshuo.cloud.service.UserService;
import com.renshuo.cloud.config.JwtCredentialsMatcher;
import com.renshuo.cloud.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

/**
 * Created by Lenovo on 2023/8/1.
 */
//@Component
@Slf4j
//@NoArgsConstructor
public class ShiroRealm extends AuthorizingRealm {

    @Value("${jwtSalt:salt}")
    private String jwtSalt;

    @Autowired
    private UserService userService;

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//        //构建凭证匹配对象
//        HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
//        //设置加密算法
//        cMatcher.setHashAlgorithmName("MD5");
//        //设置加密次数
//        cMatcher.setHashIterations(1);
        JwtCredentialsMatcher jwtCredentialsMatcher = new JwtCredentialsMatcher();
        super.setCredentialsMatcher(jwtCredentialsMatcher);
    }

    /**
     * 添加支持自定义token
     *
     * @param token token
     * @return 是否支持
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        if (token instanceof JwtToken) {
            return true;
        }
        return super.supports(token);
    }

    /**
     * 通过此方法负责授权信息的获取和封装,获取用户的权限信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("2、登录成功后进入doGetAuthorizationInfo，进行用户权限集合获取");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        if (principalCollection.getPrimaryPrincipal() instanceof JwtToken) {
            JwtToken jwtToken = (JwtToken)principalCollection.getPrimaryPrincipal();
            String tokenType = jwtToken.getTokenType();
            String token = jwtToken.getToken();
            if (JwtToken.USER_TYPE.equals(tokenType)) {
                if (token.startsWith("Bearer ")){
                    token = token.replace("Bearer ", "");
                }
                User user = JwtUtil.getUserInfo(token, jwtSalt);
//                 根据token解析用户信息查询用户所拥有的的权限列表，这里只是测试数据
                Set<String> userPermissions = userService.findUserPermissions(user.getId());
                info.setStringPermissions(userPermissions);
            } else {

                //api
            }
        }else {
            User user = (User) principalCollection.getPrimaryPrincipal();
            Set<String> userPermissions = userService.findUserPermissions(user.getId());
            info.setStringPermissions(userPermissions);
        }
        return info;
    }

    /**
     * 通过此方法完成认证数据的获取及封装,系统底层会将认证数据传递认证管理器，由认证管理器完成认证操作。
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("1、登录先进入doGetAuthenticationInfo，进行用户登录校验");

        if (authenticationToken instanceof JwtToken) {
            JwtToken jwtToken = (JwtToken) authenticationToken;
            // 用户TOKEN 授权
            String tokenType = jwtToken.getTokenType();
            try {
                if (JwtToken.USER_TYPE.equals(tokenType) || JwtToken.API_TYPE.equals(tokenType)) {
                    validateToken(jwtToken);
                } else {
                    log.error("不合法的token");
                    throw new AuthenticationException("不合法的token");
                }
            } catch (Exception e) {
                log.error("tokenType:{} 校验异常:{}", tokenType, e.getMessage());
                throw new AuthenticationException("token校验失败", e);
            }
            return new SimpleAuthenticationInfo(jwtToken, authenticationToken, getName());
        }
        throw new AuthenticationException("token不合法.");
//        else {
//
//
//            UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
//            String username = upToken.getUsername();
//            User user = userService.findUserByUsername(username);
//            if (user == null)
//                throw new UnknownAccountException();//账户不存在
//            if (user.getLocked() == 1) throw new LockedAccountException();
//
//
//            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
//
//            SimpleAuthenticationInfo sa = new SimpleAuthenticationInfo(user,
//                    user.getPassword(), credentialsSalt, getName());
//
//            return sa;
//        }
    }

    public boolean validateToken(JwtToken jwtToken) {
        String token = jwtToken.getToken();
        if (token.startsWith("Bearer ")){
            token = token.replace("Bearer ", "");
        }
        boolean expireFlag = JwtUtil.isExpiration(token, jwtSalt);
        if (expireFlag) {
            throw new TokenExpiredException("token过期");
        }
        return true;
    }
}
