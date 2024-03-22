package com.renshuo.nio.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Lenovo on 2023/6/25.
 */
public class MainStarter {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

        Car car = (Car) context.getBean("car");
        car.setName("111");
        System.out.println(car);


    }

}
