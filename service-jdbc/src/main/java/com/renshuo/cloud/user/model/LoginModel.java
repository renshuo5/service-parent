package com.renshuo.cloud.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


@Data
@ApiModel(value = "LoginModel", description = "用户登入Model")
public class LoginModel {
    /** 用户名 **/
    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名" )
    private String username;
    /** 密码 **/
    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码" )
    private String password;
    /** 验证码 **/
    @NotEmpty(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码" )
    private String validCode;
    /** 手机号 **/
    @ApiModelProperty(value = "手机号" )
    private String mobile;

    /** 是否app登录  1 是 0 否 **/
    @ApiModelProperty(value = "是否app登录  1 是 0 否" )
    private String isApp;
}
