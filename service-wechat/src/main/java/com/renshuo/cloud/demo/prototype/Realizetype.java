package com.renshuo.cloud.demo.prototype;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class Realizetype implements Cloneable {

    public Realizetype(){
        System.out.println("具体原型创建成功！");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功！");
        return (Realizetype) super.clone();
    }
}
