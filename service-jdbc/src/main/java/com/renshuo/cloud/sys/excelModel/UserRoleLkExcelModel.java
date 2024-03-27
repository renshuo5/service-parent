package com.renshuo.cloud.sys.excelModel;

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
* @description: 用户角色|用户角色中间表|urlk导出模型
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserRoleLkExcelModel", description = "用户角色|用户角色中间表|urlkExcelModel")
public class UserRoleLkExcelModel {

    @ApiModelProperty(value = "用户Id")
    @ExcelProperty("用户Id")
    private String userId;

    @ApiModelProperty(value = "角色Id")
    @ExcelProperty("角色Id")
    private String roleId;


    /**
    * 将实体转为前台model
    *
    * @param entry {@link UserRoleLk}
    * @return {@link UserRoleLkExcelModel}
    */
    public static UserRoleLkExcelModel fromEntry(UserRoleLk entry) {
        UserRoleLkExcelModel m = new UserRoleLkExcelModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}