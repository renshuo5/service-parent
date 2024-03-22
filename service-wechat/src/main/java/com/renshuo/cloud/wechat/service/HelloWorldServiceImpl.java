package com.renshuo.cloud.wechat.service;

import com.renshuo.cloud.tuling.scope.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lenovo on 2021/4/29.
 */
public class HelloWorldServiceImpl implements IHelloWorldService {


    @Override
    public  String sayHello(String name) {
        return "hello,"+name;
    }

    @Override
    public Person getPerson(String name) {
        Person p =new Person();
        p.setName(name);
        p.setAge(24);
        return p;
    }

    @Override
    public List<Person> getPersonList() {
        List<Person> list = new ArrayList<>();
        String[] strings = {"tom", "tony", "lilei","hanmeimei","zhangsan","lisi","王五"};
//        Random ran = new Random();
//        int i = ran.nextInt(7);
//        String name = strings[i];


        for(int i=1;i<11;i++){
            Random ran = new Random();
            int ss = ran.nextInt(6);
            String name = strings[ss];
            Person p =new Person();
            p.setName(name);
            p.setAge(i);
            list.add(p);
        }

        return list;
    }

    @Override
    public String getXml(String param) {
        System.out.println(param);
        String str = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                "<SstResponse>" +
                "<Code>1000000</Code>" +
                "<Message></Message>" +
                "<Total>7</Total>" +
                "<ReturnValue size=\"7\">" +
                "<Resultset>" +
                "<param1>响应参数</param1>" +
                "<param2>响应参数2</param2>" +
                "<param3>响应参数3</param3>" +
                "<param4>响应参数4</param4>" +
                "</Resultset>" +
                "</ReturnValue>" +
                "</SstResponse>";
        return str;
    }
}
