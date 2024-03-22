package com.renshuo.cloud.tuling.compentscan;

import com.renshuo.cloud.tuling.compentscan.MainConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Lenovo on 2021/3/19.
 */
public class Test {

    public static void main(String[] args) {
//        SpringApplication.run(Test.class,args);
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);

        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("bean的name："+beanDefinitionName);
        }

        System.out.println("123");
    }
}
