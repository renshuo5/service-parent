package com.renshuo.cloud.sys.excelModel;

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
* @description: 字典类别|字典类别|dc导出模型
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DictCategoryExcelModel", description = "字典类别|字典类别|dcExcelModel")
public class DictCategoryExcelModel {

    @ApiModelProperty(value = "名称")
    @ExcelProperty("名称")
    private String name;

    @ApiModelProperty(value = "编码")
    @ExcelProperty("编码")
    private String code;

    @ApiModelProperty(value = "备注")
    @ExcelProperty("备注")
    private String note;


    /**
    * 将实体转为前台model
    *
    * @param entry {@link DictCategory}
    * @return {@link DictCategoryExcelModel}
    */
    public static DictCategoryExcelModel fromEntry(DictCategory entry) {
        DictCategoryExcelModel m = new DictCategoryExcelModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}