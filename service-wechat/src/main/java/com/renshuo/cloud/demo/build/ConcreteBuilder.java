package com.renshuo.cloud.demo.build;

/**
 * Created by Lenovo on 2021/2/24.
 */
public class ConcreteBuilder extends Builder {

    private Product product=new Product();
    @Override
    public void builderPartA() {
        product.add("部件A");

    }

    @Override
    public void builderPartB() {
        product.add("部件B");

    }

    @Override
    public Product result() {
        return product;
    }
}
