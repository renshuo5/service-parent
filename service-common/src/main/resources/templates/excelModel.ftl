package com.renshuo.cloud.${domain.module}.excelModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import com.renshuo.cloud.${domain.module}.domain.${domain.name};
<#list domain.importList as imp>
import ${imp};
</#list>

/**
* @description: ${domain.comments}导出模型
* @author: renshuo
* @date: ${date}
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "${domain.name}ExcelModel", description = "${domain.comments}ExcelModel")
public class ${domain.name}ExcelModel {

<#list domain.fields as field>
    <#if field.attr!='id' && field.attr!='updateBy' && field.attr!='updateTime' && field.attr!='createBy' && field.attr!='createTime'  >
    @ApiModelProperty(value = "${field.comment}")
    @ExcelProperty("${field.comment}")
    private ${field.type} ${field.attr};

    </#if>
</#list>

    /**
    * 将实体转为前台model
    *
    * @param entry {@link ${domain.name}}
    * @return {@link ${domain.name}ExcelModel}
    */
    public static ${domain.name}ExcelModel fromEntry(${domain.name} entry) {
        ${domain.name}ExcelModel m = new ${domain.name}ExcelModel();
        BeanUtils.copyProperties(entry, m);
        return m;
    }


}