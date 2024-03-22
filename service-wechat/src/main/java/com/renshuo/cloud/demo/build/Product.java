package com.renshuo.cloud.demo.build;

import org.apache.tomcat.util.descriptor.InputSourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2021/2/24.
 */
public class Product {

    private List<String> parts = new ArrayList<>();

    public void add(String part){
        parts.add(part);
    }

    public void show(){
        System.out.println("产品创建--");
        for (String part : parts) {

            System.out.println(part);
        }
    }
}
