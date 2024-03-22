package com.renshuo.cloud.${domain.module}.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
<#list domain.importList as il>
import ${il};
</#list>

/**
* @description: ${domain.comments}实体
* @author: renshuo
* @date: ${date}
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ${domain.name} {

<#list domain.fields as field>
    <#if field.comment??>/** ${field.comment} **/</#if>
    private ${field.type} ${field.attr};

</#list>

}
