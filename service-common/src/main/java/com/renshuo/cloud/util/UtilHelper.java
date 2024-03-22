package com.renshuo.cloud.util;


import java.util.UUID;

/**
 * 通用的工具类
 */
public class UtilHelper {

    /**
     * 生成uuid并返回
     * @return
     */
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return temp;
    }

}
