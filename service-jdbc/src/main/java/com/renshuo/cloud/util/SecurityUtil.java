package com.renshuo.cloud.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

/**
 * md5摘要加密
 */
public class SecurityUtil {

    private static final String SALT = "";

    private static final String ALGORITH_NAME = "md5";

    private static final int HASH_ITERATIONS = 1;//1次哈希

//    public static String encrypt(String pswd) {
//        String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
//        return newPassword;
//    }
//
//    public static String encrypt(String username, String pswd) {
//        String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
//                HASH_ITERATIONS).toHex();
//        return newPassword;
//    }


//    public static String saltedMD5(String input, String salt) {
//        try {
//            // 创建 MessageDigest 对象
//            MessageDigest md = MessageDigest.getInstance("MD5");
//
//            // 将盐值追加到输入字符串中
//            String saltedInput = input + salt;
//
//            // 将字符串转换为字节数组并计算 MD5 散列值
//            byte[] hashedBytes = md.digest(saltedInput.getBytes());
//
//            // 将字节数组转换为十六进制字符串
//            StringBuilder sb = new StringBuilder();
//            for (byte b : hashedBytes) {
//                sb.append(String.format("%02x", b));
//            }
//
//            // 返回加密后的字符串
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void main(String[] args) {
//        String input = "password";
//        String salt = "somesalt";
//
//        String encrypted = saltedMD5(input, salt);
//        System.out.println("加密后的结果: " + encrypted);
//    }

//    public static void main(String[] args) {
//        System.out.println(encrypt("admin", "1"));
//        System.out.println(encrypt("admin"));
//    }

    public static String generateMD5Password(String password) {
        try {
            // 创建MessageDigest实例并指定算法为MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将输入转换为字节数组并进行加密
            byte[] inputBytes = password.getBytes();
            byte[] encryptedBytes = md.digest(inputBytes);

            // 将加密后的字节数组转换为十六进制字符串表示
            StringBuilder sb = new StringBuilder();
            for (byte b : encryptedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateSaltedMD5Password(String password, String salt) {
        try {
            // 创建MessageDigest实例并指定算法为MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将密码和盐值拼接在一起，并转换为字节数组进行加密
            String combinedString = password + salt;
            byte[] inputBytes = combinedString.getBytes();
            byte[] encryptedBytes = md.digest(inputBytes);

            // 将加密后的字节数组转换为十六进制字符串表示
            StringBuilder sb = new StringBuilder();
            for (byte b : encryptedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String password = "admin_123!";
        String salt = "salt";

        // 生成不加盐的MD5密码
        String unsaltedPassword = generateMD5Password(password);
        System.out.println("不加盐的MD5密码：" + unsaltedPassword);

        // 生成加盐的MD5密码
        String saltedPassword = generateSaltedMD5Password(unsaltedPassword, salt);
        System.out.println("加盐的MD5密码：" + saltedPassword);

        String name = Timestamp.class.getName();
        System.out.println(name);
    }
}
