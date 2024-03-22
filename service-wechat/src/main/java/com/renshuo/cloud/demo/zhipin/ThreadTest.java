package com.renshuo.cloud.demo.zhipin;

/**
 * Created by Lenovo on 2021/4/21.
 */
public class ThreadTest {


    public static void main(String[] args) {


        Thread a = new Thread(()->{

            SyncMethod sm = new SyncMethod();
            sm.testA();
        });
        a.start();

        Thread b = new Thread(()->{

            SyncMethod sm = new SyncMethod();
            sm.testB();
        });
        b.start();
    }

}
