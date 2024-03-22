package com.renshuo.cloud.thread;

/**
 * Created by Lenovo on 2021/3/29.
 */
public class C implements Runnable {
    @Override
    public void run() {
        Foo foo = new Foo();
        foo.third();
    }
}
