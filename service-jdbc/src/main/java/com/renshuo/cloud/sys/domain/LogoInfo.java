package com.renshuo.cloud.sys.domain;

import lombok.Getter;
import lombok.Setter;

/** 
* @description: logo的内容
*
* @author: renshuo 
* @date: 2024/3/18 
*/
@Setter
@Getter
public class LogoInfo {

    private String id;
    private String title;
    private String image;
    private String href;
    private Integer enabledStatus;
}
