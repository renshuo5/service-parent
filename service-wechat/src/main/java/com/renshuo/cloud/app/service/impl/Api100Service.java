package com.renshuo.cloud.app.service.impl;

import com.renshuo.cloud.app.entity.User;
import com.renshuo.cloud.app.model.ApiResp;
import com.renshuo.cloud.app.model.BaseApiForm;
import com.renshuo.cloud.app.service.IApiService;
import com.renshuo.cloud.app.util.ApiUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Lenovo on 2021/3/3.
 */
@Service
public class Api100Service implements IApiService{


    @Override
    public ApiResp getVersion(BaseApiForm form) {
        User user =new User();
        ApiUtil.copyProperties(user,form.getMap());

        ApiResp resp= new ApiResp("1.0.0"+form.getVersion()+"----"+user);
        return resp;
    }

    @Override
    public ApiResp getNowDate(BaseApiForm form) {
        ApiResp resp= new ApiResp("时间"+form.getVersion());
        return resp;
    }
}
