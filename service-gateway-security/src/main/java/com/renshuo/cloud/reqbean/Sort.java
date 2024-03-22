package com.renshuo.cloud.reqbean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @description: 传入参数排序
 * @author: renshuo
 * @date: 2020/12/7
 */
@Getter
@Setter
public class Sort {


    /**
     * 升序
     **/
    public static final String ASC = "asc";
    /**
     * 降序
     **/
    public static final String DESC = "desc";


    /**
     * 字段名称
     **/
    private String name;
    /**
     * 排序方式
     **/
    private String order;
}

