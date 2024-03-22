package com.renshuo.cloud.sys.controller;

import com.renshuo.cloud.sys.domain.User;
import com.renshuo.cloud.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Lenovo on 2020/12/4.
 */
@RestController
@RequestMapping(value = "/sys")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public List<User> list(@RequestBody User user){

        return userService.find(user);
    }

    @GetMapping("/user/{id}")
    public User get(@PathVariable String id){
        return userService.findById(id);
    }
}
