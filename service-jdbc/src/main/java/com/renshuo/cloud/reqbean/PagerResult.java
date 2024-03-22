package com.renshuo.cloud.reqbean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
* @description: 分页返回的实体
*
* @author: renshuo 
* @date: 2020/12/7 
*/
@Getter
@Setter
public class PagerResult {

    /** 分页信息 **/
    private PagerInfo pagerInfo;
    /** 数据 **/
    private List<Object> data;
    /** 总数 **/
    private Long total;
}
