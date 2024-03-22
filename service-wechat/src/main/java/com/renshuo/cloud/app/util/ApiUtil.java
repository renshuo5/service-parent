package com.renshuo.cloud.app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renshuo.cloud.app.model.ApiResp;
import com.renshuo.cloud.app.model.BaseApiForm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实体类,map,list转为json字符串
 *
 * @author renshuo
 */
public class ApiUtil {


    private static String tomcatWebPath;
    private static String webRootPath;
    private static String rootClassPath;


    public static ApiResp error(int code, String msg) {
        return new ApiResp().setCode(code).setMsg(msg);
    }

    /**
     * 返回json数组的字符串
     *
     * @param map
     * @return
     */
    public static String getJsonStrByMap(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return res;

    }

    /**
     * 返回json数组的字符串
     *
     * @param Object obj
     * @return
     */
    public static String getJsonStrByObj(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return res;

    }

    /**
     * 返回json数组的字符串
     *
     * @param list
     * @return
     */
    public static String getStrArrByList(List<Map<String, Object>> list) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return res;
    }


    public static String getWebRootPath() {
        if (webRootPath == null)
            webRootPath = detectWebRootPath();
        return webRootPath;
    }

    private static String detectWebRootPath() {
        try {
            String path = ApiUtil.class.getResource("/").toURI().getPath();
            return new File(path).getParentFile().getParentFile().getCanonicalPath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String getTomcatWebAppsPath() {
        if (tomcatWebPath == null)
            tomcatWebPath = detectWebAppsPath();
        return tomcatWebPath;
    }

    private static String detectWebAppsPath() {
        try {
            String path = ApiUtil.class.getResource("/").toURI().getPath();
            return new File(path).getParentFile().getParentFile().getParentFile().getCanonicalPath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 根据类型拼接文件路径
     *
     * @author 任硕
     * @param type
     * @return
     */
//	public static String getUploadPath(String type) {
//		String path = ApiConstant.MEIS_PATH +  type;
//		File file = new File(ApiConstant.BASE_PATH + path);
//		if (!file.exists()) {
//			file.mkdirs();
//		}
//
//		return path;
//	}
//
//	public static String copyFile(File file,String type){
//		String filePath = ApiUtil.getUploadPath(type) + File.separator;
//		String absPath = ApiConstant.BASE_PATH + filePath;
//		File f = new File(absPath+file.getName());
//		try {
//			FileUtils.copyFile(file, f);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return filePath+file.getName();
//	}


    public static final String REGEX_MOBILE = "1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}";

    public static boolean isMobile(String mobile) {
        Pattern pattern = Pattern.compile(REGEX_MOBILE, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    /**
     * 过滤\r\n
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static void copyProperties(Object bean, Map<String, ? extends Object> properties) {
        try {
            BeanUtils.populate(bean ,properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}