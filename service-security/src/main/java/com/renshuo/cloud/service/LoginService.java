package com.renshuo.cloud.service;

import com.renshuo.cloud.common.ResultMsg;
import com.renshuo.cloud.domain.User;
import com.renshuo.cloud.model.LoginUser;
import com.renshuo.cloud.security.config.RedisCache;
import com.renshuo.cloud.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @description: 登录服务层
 * @author: renshuo
 * @date: 2023/8/7
 */
@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Value("${jwtSecret:salt}")
    private String secret;

    public ResultMsg login(User user) {
        UsernamePasswordAuthenticationToken upt = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        try {
            Authentication authenticate = authenticationManager.authenticate(upt);
            if (Objects.isNull(authenticate)) {
                throw new RuntimeException("用户名或密码错误");
            }

            LoginUser lu = (LoginUser) authenticate.getPrincipal();
            String userId = lu.getUser().getId().toString();

            String redisKey = "login:"+userId;
            //authenticate存入redis
            redisCache.setCacheObject(redisKey,lu);
            String jwt = JwtUtil.createJwt(userId, secret, -1);
            HashMap<String, String> map = new HashMap<>();
            map.put("token", jwt);
            return ResultMsg.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            ResultMsg rm = new ResultMsg();
            rm.setCode(-1);
            rm.setMsg(e.getMessage());
            return rm;
        }

    }

    public ResultMsg logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userid);
        return ResultMsg.success("退出成功");
    }

}
