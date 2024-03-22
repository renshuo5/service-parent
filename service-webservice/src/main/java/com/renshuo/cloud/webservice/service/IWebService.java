package com.renshuo.cloud.webservice.service;

import com.renshuo.cloud.webservice.mode.Person;

import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2021/4/29.
 */
public interface IWebService {

    String sayHello(String url, String methodName, String... params);

    /**
     * 查询国内航班
     * @param url
     * @return
     */
    Person getPerson(String url, String methodName, String... params);

    List<Person> getPersonList(String url, String methodName, String... params);

    List<Map> getXml(String url, String methodName, String... params);

}
