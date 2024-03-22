package com.renshuo.cloud.sys.model;

import lombok.Getter;
import lombok.Setter;

/**
 /**
 * @description: 进入系统首页展示
 *
 * @author: renshuo
 * @date: 2024/3/18
 */
@Getter
@Setter
public class HomeInfoModel {

    /**
     * "title":"首页",
     * "href":"page/welcome-1.html?t=1"
     */
    private String id;
    private String title;
    private String href;
    private Integer enabledStatus;

}
