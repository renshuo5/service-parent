package com.renshuo.cloud.sys.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.renshuo.cloud.sys.domain.RoleMenuLk;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
* @description: 角色菜单中间|角色菜单中间表|rmlk模型
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RoleMenuLkModel", description = "角色菜单中间|角色菜单中间表|rmlkModel")
public class RoleMenuLkModel {

    @ApiModelProperty(value = "主键 ")
    @ExcelIgnore
    private String id;

    @ApiModelProperty(value = "角色id")
    @ExcelProperty("角色id")
    private String roleId;

    @ApiModelProperty(value = "菜单id")
    @ExcelProperty("菜单id")
    private String menuId;

    @ApiModelProperty(value = "创建时间")
    @ExcelIgnore
    private String createTime;

    @ApiModelProperty(value = "创建人")
    @ExcelIgnore
    private String createBy;

    private List<String> menuIds;

    /**
    * 将实体转为前台model
    *
    * @param entry {@link RoleMenuLk}
    * @return {@link RoleMenuLkModel}
    */
    public static RoleMenuLkModel fromEntry(RoleMenuLk entry) {
        RoleMenuLkModel m = new RoleMenuLkModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}