package com.renshuo.cloud.demo.bridge;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class ConcreteImplementorA implements Implementor {
    @Override
    public void OperationImpl() {
        System.out.println("具体实现化(Concrete Implementor)角色被访问");
    }
}
