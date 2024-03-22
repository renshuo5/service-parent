package com.renshuo.cloud.demo.absFactory;

/**
 * Created by Lenovo on 2021/2/24.
 */
public class VolunteerFactory implements IFactory {
    @Override
    public LieFeng createLeiFeng() {
        return new Volunteer();
    }
}
