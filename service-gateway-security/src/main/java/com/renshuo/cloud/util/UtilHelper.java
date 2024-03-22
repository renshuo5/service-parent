package com.renshuo.cloud.util;


import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static void main(String[] args) {
//        PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        String encode = encoder.encode("123456");

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis() - 300000));
        System.out.println(date);
    }
}
