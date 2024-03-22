package com.renshuo.cloud.demo.build;

/**
 * Created by Lenovo on 2021/2/24.
 */
public class Director {

    public void construct(Builder builder){
        builder.builderPartA();
        builder.builderPartB();
    }
}
