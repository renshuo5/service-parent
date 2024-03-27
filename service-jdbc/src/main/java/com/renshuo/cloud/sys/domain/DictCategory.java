package com.renshuo.cloud.sys.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @description: 字典类别|字典类别|dc实体
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DictCategory {

    /** 主键 **/
    private String id;

    /** 名称 **/
    private String name;

    /** 编码 **/
    private String code;

    /** 备注 **/
    private String note;

    /** 创建时间 **/
    private String createTime;

    /** 创建人 **/
    private String createBy;

    /** 更新时间 **/
    private String updateTime;

    /** 更新人 **/
    private String updateBy;


}
