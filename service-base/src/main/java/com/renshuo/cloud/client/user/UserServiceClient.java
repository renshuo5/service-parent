package com.renshuo.cloud.client.user;

import com.renshuo.cloud.sys.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("service-user")
public interface UserServiceClient {


    @PostMapping("/user")
    List<User> find(@RequestBody User user);

    @GetMapping("/user/{id}")
    User findById(@PathVariable(value="id") String id);
}
