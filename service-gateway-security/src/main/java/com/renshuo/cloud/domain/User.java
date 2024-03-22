package com.renshuo.cloud.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/** 
* @description: 用户表
*
* @author: renshuo 
* @date: 2020/12/4 
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User implements Serializable{


    private String id;

    private String username;
    private String password;

    private String salt;

    private int locked;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

}
