package com.renshuo.cloud.sys.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @description: 字典管理|字典管理|d实体
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dict {

    /** 主键 **/
    private String id;

    /** 名称 **/
    private String name;

    /**  **/
    private String val;

    /** 类别 **/
    private String type;

    /** 备注 **/
    private String note;

    /** 组名 **/
    private String groupName;

    /** 序号 **/
    private Integer orderNum;

    /** 创建时间 **/
    private String createTime;

    /** 创建人 **/
    private String createBy;

    /** 更新时间 **/
    private String updateTime;

    /** 更新人 **/
    private String updateBy;

    /** 删除状态  **/
    private Integer delFlag;

    /** 描述  **/
    private String description;

    /** 字典编码  **/
    private String dictCode;

    /** 字典名称 **/
    private String dictName;


}
