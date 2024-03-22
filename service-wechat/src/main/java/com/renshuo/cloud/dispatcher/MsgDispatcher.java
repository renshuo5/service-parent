package com.renshuo.cloud.dispatcher;

import com.renshuo.cloud.reqbean.TextMessage;
import com.renshuo.cloud.resbean.Article;
import com.renshuo.cloud.resbean.NewsMessage;
import com.renshuo.cloud.util.MessageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2021/1/28.
 */
public class MsgDispatcher {
    public static String processMessage(Map<String, String> map) {

        String openid=map.get("FromUserName"); //用户openid
        String mpid=map.get("ToUserName");   //公众号原始ID
        //普通文本消息
        TextMessage txtmsg=new TextMessage();
        txtmsg.setToUserName(openid);
        txtmsg.setFromUserName(mpid);
        txtmsg.setCreateTime(System.currentTimeMillis());

        txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            System.out.println("==============这是文本消息！");
            txtmsg.setContent("感谢您的关注！");
            return MessageUtil.textMessageToXml(txtmsg);
        }
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            System.out.println("==============这是图片消息！");
            NewsMessage newmsg=new NewsMessage();
            newmsg.setToUserName(openid);
            newmsg.setFromUserName(mpid);
            newmsg.setCreateTime(System.currentTimeMillis());
            newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

            System.out.println("==============这是图片消息！");
            Article article=new Article();
            article.setDescription("这是图文消息1"); //图文消息的描述
            article.setPicUrl("https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/wh=450,600/sign=5d977e6c51da81cb4eb38bc96756fc20/ae51f3deb48f8c54641753b73a292df5e1fe7f66.jpg"); //图文消息图片地址
            article.setTitle("图文消息1");  //图文消息标题
            article.setUrl("https://www.cnblogs.com/gede");  //图文url链接
            List<Article> list=new ArrayList<Article>();
            list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
            newmsg.setArticleCount(list.size());
            newmsg.setArticles(list);
            return MessageUtil.newsMessageToXml(newmsg);
        }
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
            System.out.println("==============这是链接消息！");
        }
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
            System.out.println("==============这是位置消息！");
        }
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
            System.out.println("==============这是视频消息！");
        }
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
            System.out.println("==============这是语音消息！");
        }

        return null;
    }
}
