package com.renshuo.cloud.sys.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.renshuo.cloud.sys.domain.UserRoleLk;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
* @description: 用户角色|用户角色中间表|urlk模型
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserRoleLkModel", description = "用户角色|用户角色中间表|urlkModel")
public class UserRoleLkModel {

    @ApiModelProperty(value = "")
    @ExcelIgnore
    private String id;

    @ApiModelProperty(value = "用户Id")
    @ExcelProperty("用户Id")
    private String userId;

    @ApiModelProperty(value = "角色Id")
    @ExcelProperty("角色Id")
    private String roleId;

    @ApiModelProperty(value = "创建人")
    @ExcelIgnore
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @ExcelIgnore
    private String createTime;


    /**
    * 将实体转为前台model
    *
    * @param entry {@link UserRoleLk}
    * @return {@link UserRoleLkModel}
    */
    public static UserRoleLkModel fromEntry(UserRoleLk entry) {
        UserRoleLkModel m = new UserRoleLkModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}