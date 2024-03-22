package com.renshuo.cloud.tuling.scope;

import lombok.Data;

/**
 * Created by Lenovo on 2021/3/19.
 */
@Data
public class Person {


    private int id;
    private String name;
    private int age;

    public Person(){
        System.out.println("创建Person");
    }
}
