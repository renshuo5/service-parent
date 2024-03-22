package com.renshuo.cloud.service;

import com.renshuo.cloud.config.RedisCache;
import com.renshuo.cloud.domain.User;
import com.renshuo.cloud.model.LoginUser;
import com.renshuo.cloud.util.JwtUtil;
import com.renshuo.cloud.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
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
    private UserService userService;

    @Autowired
    private RedisCache redisCache;

    @Value("${jwtSecret:salt}")
    private String secret;

    public ResultMsg login(User user) {
        try {
            Mono<UserDetails> mono = userService.findByUsername(user.getUsername());
            LoginUser lu = (LoginUser) mono.block();
            if (Objects.isNull(lu)) {
                throw new RuntimeException("用户名或密码错误");
            }

            String userId = lu.getUser().getId().toString();

            String redisKey = "login:" + userId;
            //authenticate存入redis
            redisCache.setCacheObject(redisKey, lu);
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
