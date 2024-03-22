package com.renshuo.cloud.reqbean;

import com.renshuo.cloud.util.BaseMessage;

/**
 * Created by Lenovo on 2021/1/28.
 */
public class VoiceMessage  extends BaseMessage{

    // 媒体 ID
    private String MediaId;
    // 语音格式
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
