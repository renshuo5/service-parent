package com.renshuo.cloud.sys.domain;

import lombok.Getter;
import lombok.Setter;

/** 
* @description: 进入系统首页展示
*
* @author: renshuo 
* @date: 2024/3/18 
*/
@Getter
@Setter
public class HomeInfo {

    private String id;
    private String title;
    private String href;
    private Integer enabledStatus;
}
