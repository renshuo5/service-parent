package com.renshuo.cloud.sys.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @description: 用户角色|用户角色中间表|urlk实体
* @author: renshuo
* @date: 2024-03-27
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleLk {

    /**  **/
    private String id;

    /** 用户Id **/
    private String userId;

    /** 角色Id **/
    private String roleId;

    /** 创建人 **/
    private String createBy;

    /** 创建时间 **/
    private String createTime;


}
