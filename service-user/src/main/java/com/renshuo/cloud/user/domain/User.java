package com.renshuo.cloud.user.domain;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @description: 用户表
 * @author: renshuo
 * @date: 2020/12/4
 */
@Getter
@Setter
@NoArgsConstructor
public class User {

    private Integer id;

    private String userName;
    private String passWord;
    private String realName;

    public User(String username, String password) {
        this.userName = username;
        this.passWord = password;
    }

}
