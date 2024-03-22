package com.renshuo.cloud.demo.tuling;

/**
 * Created by Lenovo on 2021/9/14.
 */
public class CodeBarrier {


    int a;
    public volatile int m1 = 1;
    public volatile int m2 = 2;

    public void readAndWrite() {
        int i = m1;
        int j = m2;
        a = i + j;
        m1 = i + 1;
        m2 = j + 2;
    }


}
