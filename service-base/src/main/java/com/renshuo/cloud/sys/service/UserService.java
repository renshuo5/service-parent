package com.renshuo.cloud.sys.service;

import com.renshuo.cloud.client.user.UserServiceClient;
import com.renshuo.cloud.sys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
* @description: 用户接口
*
* @author: renshuo 
* @date: 2020/12/4 
*/
@Service
public class UserService {


    @Autowired
    private UserServiceClient userServiceClient;


    public List<User> find(User user){

        return userServiceClient.find(user);
    }

    public User findById(String id) {
        return userServiceClient.findById(id);
    }
}
