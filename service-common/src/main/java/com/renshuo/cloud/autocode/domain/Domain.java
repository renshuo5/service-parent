package com.renshuo.cloud.autocode.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 查询表信息
 * @author: renshuo
 * @date: 2023/10/31
 */
@Getter
@Setter
@NoArgsConstructor
public class Domain {

    /**
     * 模块名
     **/
    private String module;
    /**
     * 表名
     **/
    private String tableName;
    /**
     * 名称
     **/
    private String name;
    /**
     * 注释描述
     **/
    private String comments;
    /**
     * 模块描述
     **/
    private String moduleDescription;
    /**
     * alias
     **/
    private String alias;
    /**
     * 实例名-对象名称
     **/
    private String instName;

    /**
     * 属性列
     **/
    private List<Field> fields = new ArrayList<>();
    /**
     * 导入列
     **/
    private List<String> importList = new ArrayList<>();
}
