package com.renshuo.cloud.app.service;

import com.renshuo.cloud.app.model.ApiResp;
import com.renshuo.cloud.app.model.BaseApiForm;

/**
 * Created by Lenovo on 2021/3/3.
 */
public interface IApiService {


    ApiResp getVersion(BaseApiForm form);

    ApiResp getNowDate(BaseApiForm form);
}
