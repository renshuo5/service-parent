package com.renshuo.cloud.demo.bridge;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class RefinedAbstraction extends Abstraction {


    protected RefinedAbstraction(Implementor imple) {
        super(imple);
    }

    @Override
    public void Operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
        imple.OperationImpl();

    }
}
