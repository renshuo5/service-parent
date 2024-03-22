package com.renshuo.cloud.user.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/** 
* @description: 用户表
*
* @author: renshuo 
* @date: 2020/12/4 
*/
@Getter
@Setter
public class User {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String passWord;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

}
