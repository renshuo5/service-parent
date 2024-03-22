package com.renshuo.cloud.demo.product;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class Test {

    public static void main(String[] args) {

        AbstractFactory af = new ConcreteFactory1();
        Product p = af.createProduct();

        p.show();
    }
}
