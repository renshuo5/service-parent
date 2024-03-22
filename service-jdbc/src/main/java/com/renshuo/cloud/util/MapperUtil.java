package com.renshuo.cloud.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/** 
* @description:  
*
* @author: renshuo 
* @date: 2023/8/3 
*/
public class MapperUtil {


    /**
     * 将实体转为map
     * @param obj
     * @return
     */
    public static Map<String, Object> Object2Map(Object obj)  {

        if(obj == null){
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }
}
