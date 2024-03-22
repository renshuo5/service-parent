package com.renshuo.cloud.sys.domain;


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
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;

}
