package com.renshuo.cloud.demo.tuling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Lenovo on 2021/9/13.
 */
public class ThreadTest {


    private static Logger log = LoggerFactory.getLogger(ThreadTest.class);

    private  static boolean initFlag = false;

    private volatile static int count = 0;

    private static void refresh(){

        log.info("refresh data.......");
        initFlag = true;
        log.info("refresh data success.......");

    }

    public static void main(String [] args){
        Thread a= new Thread(()->{

            while(!initFlag){

                count++;
//                System.out.println("run");
            }
            log.info("线程"+Thread.currentThread().getName()+"当前线程initFlag状态改变");
        },"ThreadA");

        a.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread b = new Thread(()->{

            refresh();
        },"ThreadB");
        b.start();
    }
}
