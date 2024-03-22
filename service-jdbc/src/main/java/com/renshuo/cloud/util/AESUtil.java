package com.renshuo.cloud.util;

import jodd.util.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES加密工具类
 * Created by chenpeng on 2016-11-30.
 */
public class AESUtil {


    /**
     * 加密
     *
     * @param content
     * @param password
     * @return
     */
    public static String encrypt(String content, String password) {
        try {
            if (StringUtils.isEmpty(content))
                return "";

            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");

            String kes = Base64.encodeToString(enCodeFormat);

            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            String str = Base64.encodeToString(result);
            return str; // 加密
        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
        } catch (InvalidKeyException e) {
//            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
        } catch (BadPaddingException e) {
//            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解密
     *
     * @param str 待解密内容
     * @return
     */
    public static String decrypt(String str, String password) {
        try {
            byte[] content = Base64.decode(str);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);

            return new String(result, "UTF-8"); // 加密
        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchPaddingException e) {
        } catch (InvalidKeyException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (BadPaddingException e) {
        } catch (Exception e) {
        }
        return "";
    }


}
