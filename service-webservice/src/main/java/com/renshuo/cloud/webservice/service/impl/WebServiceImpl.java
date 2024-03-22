package com.renshuo.cloud.webservice.service.impl;

import com.renshuo.cloud.webservice.mode.Person;
import com.renshuo.cloud.webservice.service.IWebService;
import com.renshuo.cloud.webservice.service.WebServiceClient;
import com.renshuo.cloud.webservice.util.XMLFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2021/4/29.
 */
@Service
public class WebServiceImpl implements IWebService {

    private static Logger log = LoggerFactory.getLogger(WebServiceImpl.class);

    @Autowired
    WebServiceClient webserviceClient;


    @Override
    public String sayHello(String url, String methodName, String... params) {
        return null;
    }

    @Override
    public Person getPerson(String url, String methodName, String... params) {
        return null;
    }

    @Override
    public List<Person> getPersonList(String url, String methodName, String... params) {
//        String webUrl = url;
        String webUrl = "http://localhost/helloWorld?wsdl";
        try {
            String mn = "getPersonList";
            List<Person> list =(List<Person>) webserviceClient.callWebSV(webUrl, mn,params);
            log.info("获取-成功！");
            return list;
        } catch (Exception e) {
            log.error("获取-失败！", e);
            return null;
        }

    }

    @Override
    public List<Map> getXml(String url, String methodName, String... params) {
        String webUrl = "http://localhost/helloWorld?wsdl";
        try {
            String mn = "getXml";
            String str =(String) webserviceClient.callWebSV(webUrl, mn,params);
            List<Map> list = XMLFormatUtil.xmlToList(str);
            log.info("获取-成功！");
            return list;
        } catch (Exception e) {
            log.error("获取-失败！", e);
            return null;
        }
    }

}
