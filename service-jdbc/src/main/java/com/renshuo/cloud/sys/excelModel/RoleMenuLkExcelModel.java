package com.renshuo.cloud.sys.excelModel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.renshuo.cloud.sys.domain.RoleMenuLk;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
* @description: 角色菜单中间|角色菜单中间表|rmlk导出模型
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RoleMenuLkExcelModel", description = "角色菜单中间|角色菜单中间表|rmlkExcelModel")
public class RoleMenuLkExcelModel {

    @ApiModelProperty(value = "角色id")
    @ExcelProperty("角色id")
    private String roleId;

    @ApiModelProperty(value = "菜单id")
    @ExcelProperty("菜单id")
    private String menuId;


    /**
    * 将实体转为前台model
    *
    * @param entry {@link RoleMenuLk}
    * @return {@link RoleMenuLkExcelModel}
    */
    public static RoleMenuLkExcelModel fromEntry(RoleMenuLk entry) {
        RoleMenuLkExcelModel m = new RoleMenuLkExcelModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}