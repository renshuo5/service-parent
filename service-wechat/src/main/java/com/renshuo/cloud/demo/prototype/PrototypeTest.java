package com.renshuo.cloud.demo.prototype;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class PrototypeTest {

    public static void main(String [] args){
        Realizetype obj1 = new Realizetype();
        Realizetype obj2 = null;
        try {
            obj2 = (Realizetype) obj1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("obj1==obj2?" + (obj1 == obj2));
    }

}
