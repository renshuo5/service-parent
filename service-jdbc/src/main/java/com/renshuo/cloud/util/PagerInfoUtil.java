package com.renshuo.cloud.util;

import com.github.pagehelper.util.StringUtil;
import com.renshuo.cloud.reqbean.Condition;
import com.renshuo.cloud.reqbean.PagerInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
* @description: 分页参数对象工具类
*
* @author: renshuo 
* @date: 2020/12/7 
*/

public class PagerInfoUtil {


    /** 分页信息 **/
    private static ThreadLocal<PagerInfo> pageInfoThreadLocal = new ThreadLocal<PagerInfo>();

    public static Map<String, Object> pageInfoToMap(PagerInfo pagerInfo) {
        Map<String, Object> params = new HashMap();
        Iterator var2 = pagerInfo.getConditions().iterator();

        String value;
        while(var2.hasNext()) {
            Condition con = (Condition)var2.next();
            if(StringUtil.isNotEmpty(con.getValue()) && !con.getName().equals("fileMd5")) {
                value = StringUtils.trim(con.getValue());
                value = value.replaceAll("%", "\\\\%");
                params.put(con.getName(), value);
            }
        }
        params.put("sorts", pagerInfo.getSort());
        return params;
    }


    /**
     * 设置线程变量信息
     * @param info
     */
    public static void setPageInfoThreadLocal(PagerInfo info){
        pageInfoThreadLocal.set(info);
    }



    /**
     * 获取线程变量信息

     */
    public static PagerInfo getPageInfoThreadLocal(){
        return pageInfoThreadLocal.get();
    }



    /**
     * 移除
     */
    public static void removePageInfoThreadLocal(){
        pageInfoThreadLocal.remove();
    }

}
