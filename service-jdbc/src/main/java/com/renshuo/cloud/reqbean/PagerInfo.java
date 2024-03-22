package com.renshuo.cloud.reqbean;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
* @description: 分页参数信息
*
* @author: renshuo 
* @date: 2020/12/7 
*/
@Getter
@Setter
public class PagerInfo {

    /** 页序号 **/
    private Integer start;
    /** 页大小 **/
    private Integer limit;
    /** 查询条件 **/
    private List<Condition> conditions = new ArrayList<>();
    /** 排序条件 **/
    private List<Sort> sort = new ArrayList<>();
}
