package com.renshuo.cloud.spi;

/**
 * Created by Lenovo on 2024/1/8.
 */
public class DocParse implements IParse{
    @Override
    public String doParse() {

        System.out.println("执行word解析代码");
        return "doc";
    }
}
