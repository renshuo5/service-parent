package com.renshuo.cloud.sys.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @description: 角色管理|角色管理|role实体
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    /** 主键 **/
    private String id;

    /** 角色名称 **/
    private String name;

    /** 状态 1：有效 0：无效 **/
    private Integer isValid;

    /** 是否管理员 1:是 0:否 **/
    private Integer isAdmin;

    /** 首页地址 **/
    private String indexUrl;

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
