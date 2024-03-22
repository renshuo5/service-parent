package com.renshuo.cloud.reqbean;

import com.renshuo.cloud.util.BaseMessage;

/**
 * Created by Lenovo on 2021/1/28.
 */
public class TextMessage  extends BaseMessage{

    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
