package com.renshuo.cloud.demo.bridge;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class BridgeTest {

    public static void main(String[] args) {
        Implementor imple = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(imple);
        abs.Operation();
    }
}
