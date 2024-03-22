package com.renshuo.cloud.user.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2023/10/24.
 */
@Data
public class EmailModel {

    /**
     * 邮件发送方
     */
    private String sender;

    /**
     * 邮件接收方
     */
    private List<String> receiverList = new ArrayList<>();

    /**
     * 邮件抄送方
     */
    private List<String> ccList = new ArrayList<>();

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 嵌入式资源（key:contentId,value:filePath）
     */
    private Map<String, String> inlineMap = new HashMap<>();

    /**
     * 附件列表(value:filePath)
     */
    private List<String> attachmentList = new ArrayList<>();

    public String[] getReceiverArray() {
        return this.receiverList.toArray(new String[0]);
    }

    public String[] getCcArray() {
        return this.ccList.toArray(new String[0]);
    }
}
