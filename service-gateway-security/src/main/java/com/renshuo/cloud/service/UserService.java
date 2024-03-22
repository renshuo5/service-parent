package com.renshuo.cloud.service;

import com.renshuo.cloud.annation.Mybatis;
import com.renshuo.cloud.dao.UserMapper;
import com.renshuo.cloud.domain.User;
import com.renshuo.cloud.model.LoginUser;
import com.renshuo.cloud.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description: 用户接口
 * @author: renshuo
 * @date: 2020/12/4
 */
@Service
@Mybatis(namespace = "com.renshuo.cloud.dao.UserMapper")
public class UserService extends BaseService  implements ReactiveUserDetailsService{



    //未添加baseservice时候代码开发模式
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    public User findUserByUsername(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        return this.unique("findUserByUsername", username);
    }


    public Set<String> findUserPermissions(String userId) {
        return userMapper.findUserPermissions(userId);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = findUserByUsername(username);
//        Set<String> userPermissions = findUserPermissions(user.getId());
//        LoginUser lu = new LoginUser(user,userPermissions);
//        return lu;
//    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        User user = findUserByUsername(username);
        Set<String> userPermissions = findUserPermissions(user.getId());
        LoginUser lu = new LoginUser(user,userPermissions);
        return Mono.just(lu);
    }


}
