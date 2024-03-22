package com.renshuo.cloud.autocode.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 生成代码中查询表中的列信息
 * @author: renshuo
 * @date: 2023/10/31
 */
@Getter
@Setter
public class Field {
    /**
     * 列名称
     **/
    private String name;
    /**
     * 字段属性名称
     **/
    private String attr;
    /**
     * 列类型
     **/
    private String type;
    /**
     * 列字段全路径类型，用于导入包，import使用
     **/
    private String allType;
    /**
     * 列描述
     **/
    private String comment;
    /**
     * 最大长度
     **/
    private int maxLength;
    /**
     * 是否为空
     **/
    private boolean isNullable;


}
