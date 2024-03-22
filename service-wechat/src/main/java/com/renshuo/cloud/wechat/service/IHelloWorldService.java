package com.renshuo.cloud.wechat.service;

import com.renshuo.cloud.tuling.scope.Person;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Lenovo on 2021/4/29.
 */
@WebService(serviceName="MyHelloWorldService")
public interface IHelloWorldService {

    @WebResult(name="returnHello")String sayHello(@WebParam(name="name")String name);

    @WebResult(name="returnPerson")Person getPerson(@WebParam(name="name")String name);

    @WebResult(name="returnPersonList")List<Person> getPersonList();

    @WebResult(name="returnXml")String getXml(@WebParam(name="param")String param);

}
