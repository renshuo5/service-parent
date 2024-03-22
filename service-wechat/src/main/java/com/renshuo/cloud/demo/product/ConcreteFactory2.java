package com.renshuo.cloud.demo.product;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class ConcreteFactory2 implements AbstractFactory {
    @Override
    public Product createProduct() {
        System.out.println("具体工厂2生成-->具体产品2");
        return new ConcreteProduct2();
    }
}
