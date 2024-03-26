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
@ApiModel(value = "MenuInfoSelectModel", description = "Model")
public class MenuInfoSelectModel {

    @ApiModelProperty(value = "")
    @ExcelIgnore
    private String id;

    @ApiModelProperty(value = "父菜单id")
    private String parentId;

    @ApiModelProperty(value = "是否为root节点")
    private Integer isRoot;
    @ApiModelProperty(value = "是否打开")
    private Boolean open = true;
    @ApiModelProperty(value = "是否选中")
    private Boolean checked = true;

    @ApiModelProperty(value = "菜单名称")
    private String name;


    private List<MenuInfoSelectModel> children;

    /**
     * 将实体转为前台model
     *
     * @param entry {@link MenuInfo}
     * @return {@link MenuInfoSelectModel}
     */
    public static MenuInfoSelectModel fromEntry(MenuInfo entry) {
        MenuInfoSelectModel m = new MenuInfoSelectModel();
        BeanUtils.copyProperties(entry, m);
        m.setName(entry.getTitle());
        return m;
    }


}