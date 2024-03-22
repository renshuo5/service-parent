package com.renshuo.cloud.reqbean;

import com.renshuo.cloud.util.BaseMessage;

/**
 * Created by Lenovo on 2021/1/28.
 */
public class ImageMessage extends BaseMessage {

    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
