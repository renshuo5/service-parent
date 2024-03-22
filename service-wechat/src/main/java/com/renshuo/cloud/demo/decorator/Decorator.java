package com.renshuo.cloud.demo.decorator;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
