package com.renshuo.cloud.domain;


import io.swagger.annotations.ApiModelProperty;
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


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    private String salt;

    @ApiModelProperty(value = "是否锁定，0：否，1是")
    private int locked;

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }

}
