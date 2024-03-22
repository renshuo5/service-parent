package com.renshuo.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Lenovo on 2021/1/29.
 */
@FeignClient(name="wechat",url="https://api.weixin.qq.com")
public interface WeCahtTokenClient {


    @RequestMapping(value = "/cgi-bin/token",method = RequestMethod.GET)
    String getWechatToken(@RequestParam("grant_type")String grantType,@RequestParam("appid")String appId,@RequestParam("secret")String secret);

    //?grant_type=client_credential&appid=wx6984d5b40d846fcc&secret=8b911a577273b10ff01e13e9443fc4b9


    @PostMapping(value="/cgi-bin/menu/create")
    String createMenu(@RequestParam("access_token")String accessToken, @RequestBody String menu);


}
