package com.renshuo.cloud.app.entity;

import lombok.Data;

/**
 * Created by Lenovo on 2021/3/3.
 */
@Data
public class User {


    private String id;
    private String name;
    private int age;
    private String address;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
