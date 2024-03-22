package com.renshuo.cloud.annation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @description: 定义mapper的命名空间,放到各个service类头部使用
*
* @author: renshuo 
* @date: 2020/12/7 
*/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mybatis {
    String namespace();
}
