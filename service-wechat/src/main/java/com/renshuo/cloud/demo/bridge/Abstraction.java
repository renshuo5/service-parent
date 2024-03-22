package com.renshuo.cloud.demo.bridge;

/**
 * Created by Lenovo on 2021/3/4.
 */
public abstract class Abstraction {

    protected Implementor imple;

    protected Abstraction(Implementor imple) {
        this.imple = imple;
    }

    public abstract void Operation();
}
