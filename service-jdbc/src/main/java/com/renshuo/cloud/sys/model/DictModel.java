package com.renshuo.cloud.sys.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.renshuo.cloud.sys.domain.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
* @description: 字典管理|字典管理|d模型
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DictModel", description = "字典管理|字典管理|dModel")
public class DictModel {

    @ApiModelProperty(value = "主键")
    @ExcelIgnore
    private String id;

    @ApiModelProperty(value = "名称")
    @ExcelProperty("名称")
    private String name;

    @ApiModelProperty(value = "")
    @ExcelProperty("")
    private String val;

    @ApiModelProperty(value = "类别")
    @ExcelProperty("类别")
    private String type;

    @ApiModelProperty(value = "备注")
    @ExcelProperty("备注")
    private String note;

    @ApiModelProperty(value = "组名")
    @ExcelProperty("组名")
    private String groupName;

    @ApiModelProperty(value = "序号")
    @ExcelProperty("序号")
    private Integer orderNum;

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

    @ApiModelProperty(value = "删除状态 ")
    @ExcelProperty("删除状态 ")
    private Integer delFlag;

    @ApiModelProperty(value = "描述 ")
    @ExcelProperty("描述 ")
    private String description;

    @ApiModelProperty(value = "字典编码 ")
    @ExcelProperty("字典编码 ")
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    @ExcelProperty("字典名称")
    private String dictName;


    /**
    * 将实体转为前台model
    *
    * @param entry {@link Dict}
    * @return {@link DictModel}
    */
    public static DictModel fromEntry(Dict entry) {
        DictModel m = new DictModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}