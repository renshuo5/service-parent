package com.renshuo.cloud.user.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.renshuo.cloud.user.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Lenovo on 2023/11/7.
 */
@Setter
@Getter
@NoArgsConstructor
@ApiModel(value = "UserModel", description = "用户信息Model")
public class UserModel implements Serializable {

    @ApiModelProperty(value = "主键")
    @ExcelIgnore
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @ExcelProperty("用户名")
    @NotEmpty
    @NotNull
    private String userName;
    @ApiModelProperty(value = "用户密码")
    @ExcelIgnore
    private String passWord;

    @ApiModelProperty(value = "真实姓名")
    @ExcelProperty("真实姓名")
    private String realName;


    /**
     * 将实体转为前台model
     *
     * @param entry {@link User}
     * @return {@link UserModel}
     */
    public static UserModel fromEntry(User entry) {
        UserModel m = new UserModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }

}
