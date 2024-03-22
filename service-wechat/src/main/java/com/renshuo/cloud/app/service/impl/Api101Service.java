package com.renshuo.cloud.app.service.impl;

import com.renshuo.cloud.app.model.ApiResp;
import com.renshuo.cloud.app.model.BaseApiForm;
import com.renshuo.cloud.app.service.IApiService;
import org.springframework.stereotype.Service;

/**
 * Created by Lenovo on 2021/3/3.
 */
@Service
public class Api101Service extends Api100Service implements IApiService {

    @Override
    public ApiResp getVersion(BaseApiForm form) {
        ApiResp resp= new ApiResp("1.0.1"+form.getVersion()+"-----ip:"+form.getIp());
        return resp;
    }

    @Override
    public ApiResp getNowDate(BaseApiForm form) {
        ApiResp resp= new ApiResp("日期时间"+form.getVersion());
        return resp;
    }
}
