package com.renshuo.cloud.sys.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: logo的内容
 *
 * @author: renshuo
 * @date: 2024/3/18
 */
@Getter
@Setter
public class LogoInfoModel {


    /**
     * "title": "LAYUI MINI",
     * "image": "images/logo.png",
     * "href": ""
     */
    private String id;
    private String title;
    private String image;
    private String href;
    private Integer enabledStatus;

}
