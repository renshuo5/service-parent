package com.renshuo.cloud.demo.zhipin;

/**
 * Created by Lenovo on 2021/4/21.
 */
public class SyncMethod {


    public synchronized void testA(){
        System.out.println("====testA====");

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====testA end====");
    }

    public synchronized void testB(){
        System.out.println("====testB====");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====testB end====");
    }
}
