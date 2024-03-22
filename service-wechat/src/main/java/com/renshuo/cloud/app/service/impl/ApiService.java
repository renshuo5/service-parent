package com.renshuo.cloud.app.service.impl;

import com.renshuo.cloud.app.model.ApiResp;
import com.renshuo.cloud.app.service.IApiService;
import com.renshuo.cloud.app.util.ApiConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2021/3/3.
 */
@Service
public class ApiService {

    private Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private IApiService api100Service;

    @Autowired
    private IApiService api101Service;


    private Map<String,IApiService> map = new HashMap<>();


    @PostConstruct
    public void initApiVersionService(){
        map.put("1.0.0",api100Service);
        map.put("1.0.1",api101Service);
    }

    public IApiService getApiServiceInstance(String version){
        return map.get(version);
    }

    /**
     * 版本错误返回resp
     *
     *
     * @return
     */
    public ApiResp getVersionErrorResp() {
        return new ApiResp().setCode(ApiConstant.CODE_VERSION_ERROR).setMsg(ApiConstant.MSG_VERSION_ERROR);
    }



}
