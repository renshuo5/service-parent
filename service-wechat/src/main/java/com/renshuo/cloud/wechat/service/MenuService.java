package com.renshuo.cloud.wechat.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.renshuo.cloud.bean.TokenBean;
import com.renshuo.cloud.feign.WeCahtTokenClient;
import com.renshuo.cloud.menu.ClickButton;
import com.renshuo.cloud.menu.ViewButton;
import jdk.nashorn.internal.runtime.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lenovo on 2021/1/29.
 */
@Component
public class MenuService {

    @Autowired
    private WeCahtTokenClient weChatTokenClient;

    public String createMenu(){
        ClickButton cbt=new ClickButton();
        cbt.setKey("image");
        cbt.setName("回复图片");
        cbt.setType("click");


        ViewButton vbt=new ViewButton();
        vbt.setName("博客");
        vbt.setType("view");

        JSONArray sub_button=new JSONArray();
        sub_button.add(cbt);
        sub_button.add(vbt);


        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "菜单");
        buttonOne.put("sub_button", sub_button);

        JSONArray button=new JSONArray();
        button.add(vbt);
        button.add(buttonOne);
        button.add(cbt);

        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
        System.out.println(menujson);

        String token = TokenBean.getInstance().getToken();
        String menu = weChatTokenClient.createMenu(token, menujson.toJSONString());
        System.out.println(menu);

        return menu;
//        //这里为请求接口的url   +号后面的是token，这里就不做过多对token获取的方法解释
//        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ GlobalConstants.getInterfaceUrl("access_token");
//
//        try{
//            String rs=HttpUtils.sendPostBuffer(url, menujson.toJSONString());
//            System.out.println(rs);
//        }catch(Exception e){
//            System.out.println("请求错误！");
//        }
    }
}
