package com.renshuo.cloud.demo.prototype;

/**
 * Created by Lenovo on 2021/3/4.
 */
public class CitationTest {

    public static void main(String [] args){
        Citation c = new Citation("张三","三好学生","学院");

        c.display();
        try {
            Citation clone = (Citation)  c.clone();
            clone.setCollege("2学院");
            clone.setName("李四");
            clone.setInfo("五好学生");
            clone.display();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }
}
