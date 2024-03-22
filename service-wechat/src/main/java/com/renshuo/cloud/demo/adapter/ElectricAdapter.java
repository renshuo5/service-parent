package com.renshuo.cloud.demo.adapter;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class ElectricAdapter implements Motor {

    private ElectricMotor emotor;

    public ElectricAdapter() {
        emotor = new ElectricMotor();
    }

    @Override
    public void drive() {
        emotor.electricDrive();
    }
}
