package com.renshuo.cloud.sys.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.renshuo.cloud.sys.domain.MenuInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @description: 模型
 * @author: renshuo
 * @date: 2024-03-18
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MenuInfoModel", description = "Model")
public class MenuInfoModel {

    @ApiModelProperty(value = "")
    @ExcelIgnore
    private String id;

    @ApiModelProperty(value = "父菜单id")
    @ExcelProperty("父菜单id")
    private String parentId;

    @ApiModelProperty(value = "菜单的id全路径")
    @ExcelProperty("菜单的id全路径")
    private String path;

    @ApiModelProperty(value = "是否为root节点")
    @ExcelProperty("是否为root节点")
    private Integer isRoot;

    @ApiModelProperty(value = "code设置于权限")
    @ExcelProperty("code设置于权限")
    private String code;

    @ApiModelProperty(value = "菜单名称")
    @ExcelProperty("菜单名称")
    private String title;

    @ApiModelProperty(value = "图标")
    @ExcelProperty("图标")
    private String icon;

    @ApiModelProperty(value = "链接")
    @ExcelProperty("链接")
    private String href;

    @ApiModelProperty(value = "")
    @ExcelProperty("")
    private String target;

    @ApiModelProperty(value = "是否功能 1 功能 0:模块")
    @ExcelProperty("是否功能 1 功能 0:模块")
    private Integer isFun;

    @ApiModelProperty(value = "排序")
    @ExcelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty(value = "是否隐藏")
    @ExcelProperty("是否隐藏")
    private Integer isHidden;

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

    private List<MenuInfoModel> child;

    /**
     * 将实体转为前台model
     *
     * @param entry {@link MenuInfo}
     * @return {@link MenuInfoModel}
     */
    public static MenuInfoModel fromEntry(MenuInfo entry) {
        MenuInfoModel m = new MenuInfoModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}