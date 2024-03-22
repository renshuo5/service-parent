package com.renshuo.cloud.demo.decorator;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        Component p = new ConcreteComponent();
        p.operation();
        System.out.println("---------------------------------");
        Component d = new ConcreteDecorator(p);
        d.operation();
    }
}
