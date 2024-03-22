package com.renshuo.cloud.webservice.service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;

/**
 * Created by Lenovo on 2021/4/29.
 */

@Component
public class WebServiceClient {

    // 动态调用webservice接口
    public Object callWebSV(String wsdUrl, String operationName, String... params) throws Exception {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdUrl);
        //此处可以添加命名空间下的方法名
//        QName name = new QName("http://service.wechat.cloud.renshuo.com/", operationName);
        // 需要密码的情况需要加上用户名和密码
        //client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects;
        // invoke("方法名",参数1,参数2,参数3....);
        objects = client.invoke(operationName, params);
        return objects[0];
    }

//    public String callWebSV(String wsdUrl, String operationName)  throws Exception{
//        // 创建动态客户端
////        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
////        QName SERVICE_NAME = new QName("http://service.wechat.cloud.renshuo.com/", "sayHello");
////        Client client = dcf.createClient(wsdUrl,SERVICE_NAME);
////        Object[] objects = client.invoke(operationName,null);
////        return objects[0].toString();
//
//
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//        org.apache.cxf.endpoint.Client client = dcf
//                .createClient("http://localhost/helloWorld?wsdl");
//        // url为调用webService的wsdl地址
//        // namespace是命名空间，methodName是方法名
//        QName name = new QName("http://service.wechat.cloud.renshuo.com/", operationName);
//        String xmlStr = "aaaaaaaa";
//        // paramvalue为参数值
//        Object[] objects;
//        objects = client.invoke(name, xmlStr);
//        System.out.println(objects[0].toString());
//
//        return objects[0].toString();
//    }

}
