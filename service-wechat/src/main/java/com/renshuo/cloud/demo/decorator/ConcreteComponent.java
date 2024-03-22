package com.renshuo.cloud.demo.decorator;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("调用具体构件角色的方法operation()");
    }

    public ConcreteComponent() {
        System.out.println("创建具体构件角色");
    }

}
