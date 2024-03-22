package com.renshuo.cloud.demo.adapter;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class MotorAdapterTest {


    public static void main(String[] args)
    {
        System.out.println("适配器模式测试：");
//        Motor motor=new ElectricAdapter();
        Motor motor=new OpticalAdapter();
        motor.drive();
    }
}
