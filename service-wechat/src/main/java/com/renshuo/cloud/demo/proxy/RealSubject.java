package com.renshuo.cloud.demo.proxy;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("访问真实主题方法...");

    }
}
