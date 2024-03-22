package com.renshuo.cloud.demo.facade;

import java.math.BigDecimal;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class FacadePattern {

    public static void main(String[] args) {
        Facade f = new Facade();
        f.method();

        double i = 0.1*5;
        System.out.println(i);
        int ii = (int) (0.1*5);
        System.out.println(ii);

        double a = 0.1 + 0.2;
        System.out.println(0.1 + 0.2);
        System.out.println(a);
        System.out.println("======");
        System.out.println( 0.1*5);

        System.out.println("======");

        System.out.println(0.1 * 0.2);
        System.out.println(0.2 / 0.1);

        System.out.println(0.3 - 0.1);
        System.out.println(0.3 / 0.1);


        BigDecimal b = new BigDecimal(Double.toString(0.1));

        BigDecimal multiply = b.multiply(new BigDecimal(5));
        System.out.println(multiply);

    }
}
