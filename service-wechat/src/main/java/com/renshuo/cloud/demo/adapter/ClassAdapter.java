package com.renshuo.cloud.demo.adapter;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        specificRequest();
    }
}
