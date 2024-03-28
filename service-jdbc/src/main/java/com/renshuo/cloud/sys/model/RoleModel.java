package com.renshuo.cloud.sys.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.renshuo.cloud.sys.domain.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
* @description: 角色管理|角色管理|role模型
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RoleModel", description = "角色管理|角色管理|roleModel")
public class RoleModel {

    @ApiModelProperty(value = "主键")
    @ExcelIgnore
    private String id;

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

    @ApiModelProperty(value = "创建时间")
    @ExcelIgnore
    private String createTime;

    @ApiModelProperty(value = "创建人")
    @ExcelIgnore
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @ExcelIgnore
    private String updateTime;

    @ApiModelProperty(value = "更新人")
    @ExcelIgnore
    private String updateBy;

    private List<String> menuIds;


    /**
    * 将实体转为前台model
    *
    * @param entry {@link Role}
    * @return {@link RoleModel}
    */
    public static RoleModel fromEntry(Role entry) {
        RoleModel m = new RoleModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}