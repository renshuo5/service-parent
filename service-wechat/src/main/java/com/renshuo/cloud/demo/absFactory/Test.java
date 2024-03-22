package com.renshuo.cloud.demo.absFactory;

/**
 * Created by Lenovo on 2021/2/24.
 */
public class Test {


    public static void main(String [] args){
        //1、抽象工厂模式
        IFactory studentFactory = new StudentFactory();
        LieFeng leiFeng = studentFactory.createLeiFeng();
        leiFeng.buyRice();


        IFactory volunteerFactory = new VolunteerFactory();

        LieFeng leiFeng1 = volunteerFactory.createLeiFeng();
        leiFeng1.sweep();

    }

}
