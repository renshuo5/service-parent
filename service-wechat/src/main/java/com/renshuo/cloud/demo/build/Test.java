package com.renshuo.cloud.demo.build;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

/**
 * Created by Lenovo on 2021/2/24.
 */
public class Test {

    public static void main(String [] args){


        Director di = new Director();
        Builder b1=new ConcreteBuilder();
        Builder b2 = new ConcreteBuilder2();

        di.construct(b1);
        Product result = b1.result();
        result.show();

        di.construct(b2);
        Product result2 = b2.result();
        result2.show();

    }
}
