package com.renshuo.cloud.sys.excelModel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.renshuo.cloud.sys.domain.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
* @description: 角色管理|角色管理|role导出模型
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RoleExcelModel", description = "角色管理|角色管理|roleExcelModel")
public class RoleExcelModel {

    @ApiModelProperty(value = "角色名称")
    @ExcelProperty("角色名称")
    private String name;

    @ApiModelProperty(value = "状态 1：有效 0：无效")
    @ExcelProperty("状态 1：有效 0：无效")
    private Integer isValid;

    @ApiModelProperty(value = "是否管理员 1:是 0:否")
    @ExcelProperty("是否管理员 1:是 0:否")
    private Integer isAdmin;

    @ApiModelProperty(value = "首页地址")
    @ExcelProperty("首页地址")
    private String indexUrl;

    @ApiModelProperty(value = "备注")
    @ExcelProperty("备注")
    private String note;


    /**
    * 将实体转为前台model
    *
    * @param entry {@link Role}
    * @return {@link RoleExcelModel}
    */
    public static RoleExcelModel fromEntry(Role entry) {
        RoleExcelModel m = new RoleExcelModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}