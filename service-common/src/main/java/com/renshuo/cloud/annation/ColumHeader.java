package com.renshuo.cloud.annation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 获取导出的字段的信息
 * @author: renshuo
 * @date: 2023/10/12
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumHeader {
    /**
     * 标题
     *
     * @return
     */
    String title() default "";

    /**
     * 示例
     *
     * @return
     */
    String example() default "";

    /**
     * 说明
     *
     * @return
     */
    String desc() default "";

    /**
     * 填写不能为空
     *
     * @return
     */
    boolean notNull() default false;

}
