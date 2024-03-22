package com.renshuo.cloud.util;

import com.renshuo.cloud.common.model.CsrfInfo;
import jodd.json.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CommonUtil {


    public static String createCsrf(String aesKey) {
        CsrfInfo csrfInfo = new CsrfInfo();
        csrfInfo.setTime(System.currentTimeMillis());
        csrfInfo.setUuid(UtilHelper.getUUID());
        //有效时间设为 一天
        csrfInfo.setValidTime(1000 * 60 * 60 * 24);
        String jsonString = new JsonSerializer().serialize(csrfInfo);
        return AESUtil.encrypt(jsonString, aesKey);
    }

    /**
     * 获取csrf值
     *
     * @return
     */
    public static String getCsrf() {
        String csrf = "";
        try {
            csrf = CookieUtils.getCookie("CSRF");
        } catch (Exception e) {
            log.error("获取不到CSRF值");
        }
        return StringUtils.isEmpty(csrf) ? "" : csrf;

    }
}
