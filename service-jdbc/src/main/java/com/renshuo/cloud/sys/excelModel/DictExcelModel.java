package com.renshuo.cloud.sys.excelModel;

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
* @description: 字典管理|字典管理|d导出模型
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DictExcelModel", description = "字典管理|字典管理|dExcelModel")
public class DictExcelModel {

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
    * @return {@link DictExcelModel}
    */
    public static DictExcelModel fromEntry(Dict entry) {
        DictExcelModel m = new DictExcelModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}