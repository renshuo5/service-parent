package com.renshuo.cloud.demo.prototype;

import lombok.Data;

/**
 * Created by Lenovo on 2021/3/4.
 */
@Data
public class Citation implements Cloneable {

    String name;
    String info;
    String college;

    public Citation(){
        System.out.println("创建奖状成功");

    }

    public Citation(String name, String info, String college) {
        this.name = name;
        this.info = info;
        this.college = college;
        System.out.println("创建奖状成功");
    }

    public void display() {
        System.out.println(name + info + college);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println("奖状拷贝成功！");
        return (Citation) super.clone();
    }

}
