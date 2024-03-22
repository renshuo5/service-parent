package com.renshuo.cloud.spi;

/**
 * Created by Lenovo on 2024/1/8.
 */
public class ExcelParse implements IParse {
    @Override
    public String doParse() {
        System.out.println("执行excel解析代码");
        return "excel";
    }
}
