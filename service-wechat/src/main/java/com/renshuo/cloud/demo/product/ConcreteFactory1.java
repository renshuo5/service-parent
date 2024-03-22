package com.renshuo.cloud.demo.product;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class ConcreteFactory1 implements AbstractFactory {
    @Override
    public Product createProduct() {
        System.out.println("具体工厂1生成-->具体产品1");
        return new ConcreteProduct1();
    }
}
