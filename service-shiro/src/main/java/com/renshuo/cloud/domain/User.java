package com.renshuo.cloud.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** 
* @description: 用户表
*
* @author: renshuo 
* @date: 2020/12/4 
*/
@Getter
@Setter
@ToString
public class User {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    private String salt;

    @ApiModelProperty(value = "是否锁定，0：否，1是")
    private int locked;

}
