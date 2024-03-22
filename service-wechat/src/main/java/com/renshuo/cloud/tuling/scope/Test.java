package com.renshuo.cloud.tuling.scope;

import com.renshuo.cloud.tuling.compentscan.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Lenovo on 2021/3/19.
 */
public class Test {

    public static void main(String[] args) {
//        SpringApplication.run(Test.class,args);
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
//        Person person = (Person)ctx.getBean("person");
//        Person person2 = (Person)ctx.getBean("person");
//        System.out.println(person == person2);

        System.out.println( test());
        String ss = "123qwe";
        String a = "123qwe";
        String bb ="1"+"22"+a;
        String b1 ="1"+"22";


        System.out.println(ss==a);

        StringBuffer sb = new StringBuffer(ss);
        sb.reverse();


    }

    private static String test() {
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            return "1";

        } finally {
            return "2";
        }
    }
}
