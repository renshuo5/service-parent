package com.renshuo.cloud.schedule;

import com.alibaba.fastjson.JSONObject;
import com.renshuo.cloud.bean.TokenBean;
import com.renshuo.cloud.feign.WeCahtTokenClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
* @description: 获取token定时任务
*
* @author: renshuo 
* @date: 2021/1/29 
*/
@Component
public class WechatTokenSchedue {


    private static final Logger log = LoggerFactory.getLogger(WechatTokenSchedue.class);

    @Autowired
    private WeCahtTokenClient weChatTokenClient;

    @Value("${wechat.appid}")
    private String appId;

    @Value("${wechat.secret}")
    private String secret;

    @Value("${wechat.grant_type}")
    private String grantType;

    @PostConstruct
    public void initToken(){
        log.info("@Postcontruct’在依赖注入完成后自动调用getToken获取微信token");
        getToken();
    }

    @Scheduled(cron="0 0 0/2 * * ?")
    public void task(){

        getToken();
    }


    public void getToken() {
        String wechatToken = weChatTokenClient.getWechatToken(grantType, appId, secret);

        log.info("token信息："+wechatToken);
        JSONObject o = JSONObject.parseObject(wechatToken);
        String token  = o.getString("access_token");
        TokenBean tokenBean = TokenBean.getInstance();
        tokenBean.setToken(token);
    }
}
