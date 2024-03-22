package com.renshuo.cloud.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.renshuo.cloud.app.model.ApiResp;
import com.renshuo.cloud.app.model.BaseApiForm;
import com.renshuo.cloud.app.service.IApiService;
import com.renshuo.cloud.app.service.impl.ApiService;
import com.renshuo.cloud.app.util.ApiUtil;
import com.renshuo.cloud.app.util.ReflectionUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lenovo on 2021/3/3.
 */

@RestController
public class AppController {

    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApiService apiService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value="api/{method}",method = { RequestMethod.GET, RequestMethod.POST })
    public ApiResp action(@PathVariable String method){

        long start = System.currentTimeMillis();
        BaseApiForm form = new BaseApiForm(request);

        form.setMethod(method);
        form.setVersion(form.get("version"));

        form.setIp(ApiUtil.getIpAddr(request));
        if(StringUtils.isBlank(form.getVersion())){
            throw new RuntimeException("version未必填项");
        }

        IApiService apiLogic = apiService.getApiServiceInstance(form.getVersion());

        ApiResp apiResp = (ApiResp) ReflectionUtil.invokeMethod(apiLogic, form.getMethod(), new Class<?>[]{BaseApiForm.class}, new Object[]{form});

        log.info("API DEBUG ACTION \n[from=" + form + "]" //
                + "\n[resp=" + JSONObject.toJSONString((apiResp)) + "]" //
                + "\n[time=" + (System.currentTimeMillis() - start) + "ms]");
        return apiResp;
    }

}
