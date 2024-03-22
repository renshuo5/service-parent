package com.renshuo.cloud.demo.adapter;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class ClassAdapterTest {

    public static void main(String[] args)
    {
        System.out.println("类适配器模式测试：");
        Target target = new ClassAdapter();
        target.request();
    }

}
