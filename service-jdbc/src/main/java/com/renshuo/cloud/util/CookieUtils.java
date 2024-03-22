package com.renshuo.cloud.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/** 
* @description: 写入cookie
*
* @author: renshuo 
* @date: 2022/12/5 
*/
public class CookieUtils {



    /**
     * 获取Reqeust
     * @return
     * 返回当前Request
     */
    public static HttpServletRequest getRequest(){

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }

    /**
     * 获取Response
     *
     * @return
     * 返回当前 Response
     */
    public static HttpServletResponse getResponse(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletResponse rsp = sra.getResponse();
        return rsp;
    }

    public static Cookie[] getCookies() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie[] c = request.getCookies();
        return c;
    }

    public static void showCookie() {
        Cookie[] c = getCookies();
        for (int i = 0; i < (c == null ? 0 : c.length); i++) {
            System.out.println("一条cookie____  name: " + c[i].getName() + "  || value: " + c[i].getValue());
        }
    }

    public static void saveCookie(Cookie cookie) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.addCookie(cookie);
    }

    /**
     * 添加cookie
     *
     * @param name
     * @param value
     */
    public static void addCookie(String name, String value) {
        try {



            Cookie cookie = new Cookie(name, value);
            cookie.setPath("/");
            // 设置保存cookie最大时长
            saveCookie(cookie);

        } catch (Exception e) {
            System.out.println(" -------添加cookie 失败！--------" + e.getMessage());
        }
    }

    /**
     * 更新cookie
     * @param name
     * @param value
     */
    public static void updateCookie(String name, String value){
        removeCookie(name);
        addCookie(name, value);
    }

    /**
     * 获取cookie
     *
     * @param name
     * @return
     */
    public static String getCookie(String name, String value) {
        try {

            Cookie[] cookies = getCookies();
            String v = null;
            for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
                if ((name).equalsIgnoreCase(cookies[i].getName())) {
                    v = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                }
            }
            if (v != null) {
                return v;
            }
        } catch (Exception e) {
            System.out.println("------获取 clazz Cookie 失败----- " + e.getMessage());
        }
        return null;
    }

    /**
     * 获取cookie
     *
     * @param name
     * @return
     */
    public static String getCookie(String name) {
        try {

            Cookie[] cookies = getCookies();

            for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
                if ((name).equalsIgnoreCase(cookies[i].getName())) {
                    return URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                }
            }
        } catch (Exception e) {
            System.out.println(" --------获取String cookie 失败--------   " + e.getMessage());
        }
        return null;
    }

    /**
     * 删除cookie
     *
     * @param name
     */
    public static void removeCookie(String name) {
        try {

            Cookie[] cookies = getCookies();

            for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
                if ((name).equalsIgnoreCase(cookies[i].getName())) {

                    Cookie cookie = new Cookie(name, "");
                    cookie.setPath("/");
                    // 设置保存cookie最大时长为0，即使其失效
                    cookie.setMaxAge(0);
                    saveCookie(cookie);
                }
            }

        } catch (Exception e) {
            System.out.println(" -------删除cookie失败！--------" + e.getMessage());
        }
    }
}
