package com.renshuo.cloud.thread;

/**
 * Created by Lenovo on 2021/3/29.
 */
public class A implements Runnable{


    @Override
    public void run() {
        Foo foo= new Foo();
        foo.first();
    }
}
