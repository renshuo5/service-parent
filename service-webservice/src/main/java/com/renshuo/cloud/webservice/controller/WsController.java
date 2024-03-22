package com.renshuo.cloud.webservice.controller;

import com.renshuo.cloud.webservice.mode.Person;
import com.renshuo.cloud.webservice.service.IWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2021/4/29.
 */
@RestController
public class WsController {

    @Autowired
    private IWebService webServiceImpl;

    @RequestMapping("/person")
    public Object getHangban(){
        String url = "";
        String methodName = "";
        String param = "123";
        Person maps = webServiceImpl.getPerson(url,methodName,param);
        return maps;
    }

    @RequestMapping("/persons")
    public Object getPersonList(){
        String url = "";
        String methodName = "";
        String param = "123";
        List<Person> maps = webServiceImpl.getPersonList(url,methodName,param);
        return maps;
    }

    @RequestMapping("/xml")
    public Object getXml(){
        String url = "";
        String methodName = "";
        String param = "123";
        List<Map> maps = webServiceImpl.getXml(url,methodName,param);
        return maps;
    }
}
