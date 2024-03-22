package com.renshuo.cloud.demo.adapter;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class OpticalAdapter implements Motor {

    private OpticalMotor omotor;
    public OpticalAdapter()
    {
        omotor=new OpticalMotor();
    }

    @Override
    public void drive() {

        omotor.opticalDrive();
    }
}
