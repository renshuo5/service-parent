package com.renshuo.cloud.sys.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.renshuo.cloud.sys.domain.DictCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
* @description: 字典类别|字典类别|dc模型
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DictCategoryModel", description = "字典类别|字典类别|dcModel")
public class DictCategoryModel {

    @ApiModelProperty(value = "主键")
    @ExcelIgnore
    private String id;

    @ApiModelProperty(value = "名称")
    @ExcelProperty("名称")
    private String name;

    @ApiModelProperty(value = "编码")
    @ExcelProperty("编码")
    private String code;

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


    /**
    * 将实体转为前台model
    *
    * @param entry {@link DictCategory}
    * @return {@link DictCategoryModel}
    */
    public static DictCategoryModel fromEntry(DictCategory entry) {
        DictCategoryModel m = new DictCategoryModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}