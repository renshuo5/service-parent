package com.renshuo.cloud.webservice.other;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * Created by Lenovo on 2021/4/29.
 */
public class WsServiceMain {


    public static void main(String [] args){
        System.out.println("web Service start");
        HelloWorldServiceImpl implementor = new HelloWorldServiceImpl();
        String address="http://localhost/helloWorld";
        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
        factoryBean.setAddress(address); //设置暴露地址
        factoryBean.setServiceClass(IHelloWorldService.class); //接口类
        factoryBean.setServiceBean(implementor); //设置实现类
        factoryBean.create();
        System.out.println("web Service started");
    }
}
