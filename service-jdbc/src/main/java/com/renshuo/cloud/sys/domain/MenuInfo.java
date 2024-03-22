package com.renshuo.cloud.sys.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @description: 实体
* @author: renshuo
* @date: 2024-03-18
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuInfo {

    /**  **/
    private String id;

    /** 父菜单id **/
    private String parentId;

    /** 菜单的id全路径 **/
    private String path;

    /** 是否为root节点 **/
    private Integer isRoot;

    /** code设置于权限 **/
    private String code;

    /** 菜单名称 **/
    private String title;

    /** 图标 **/
    private String icon;

    /** 链接 **/
    private String href;

    /**  **/
    private String target;

    /** 是否功能 1 功能 0:模块 **/
    private Integer isFun;

    /** 排序 **/
    private Integer orderNum;

    /** 是否隐藏 **/
    private Integer isHidden;

    /** 创建时间 **/
    private String createTime;

    /** 创建人 **/
    private String createBy;

    /** 更新时间 **/
    private String updateTime;

    /** 更新人 **/
    private String updateBy;


}
