package com.renshuo.cloud.sys.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @description: 角色菜单中间|角色菜单中间表|rmlk实体
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuLk {

    /** 主键  **/
    private String id;

    /** 角色id **/
    private String roleId;

    /** 菜单id **/
    private String menuId;

    /** 创建时间 **/
    private String createTime;

    /** 创建人 **/
    private String createBy;


}
