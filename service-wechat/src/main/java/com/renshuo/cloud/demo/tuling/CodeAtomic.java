package com.renshuo.cloud.demo.tuling;

/**
 * Created by Lenovo on 2021/9/13.
 */
public class CodeAtomic {

    private  static int counter =0 ;

    public  static void main(String [] args){
        for (int i=0;i<10;i++){

            Thread t = new Thread(()->{

                for (int j = 0; j < 1000; j++) {

                    counter++;
                }
            });
            t.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
    }
}
