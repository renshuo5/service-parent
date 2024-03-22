package com.renshuo.cloud.sys.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Lenovo on 2024/3/18.
 */
@Data
public class InitModel {

    private List<MenuInfoModel> menuInfo;

    private HomeInfoModel homeInfo;

    private LogoInfoModel logoInfo;

}
