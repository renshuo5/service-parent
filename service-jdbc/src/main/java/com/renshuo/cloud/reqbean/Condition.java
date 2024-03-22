package com.renshuo.cloud.reqbean;

import lombok.Getter;
import lombok.Setter;

/**
* @description: 传入参数
*
* @author: renshuo 
* @date: 2020/12/7 
*/
@Getter
@Setter
public class Condition {

    /** 查询条件名称 **/
    private String name;
    /** 查询条件值 **/
    private String value;
    /** 操作符 **/
    private String op;
}
