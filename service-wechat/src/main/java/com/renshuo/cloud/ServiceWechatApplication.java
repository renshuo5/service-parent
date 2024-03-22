package com.renshuo.cloud;

import com.alibaba.fastjson.JSONObject;
import com.renshuo.cloud.bean.TokenBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

/** 
* @description: 启动类
*
* @author: renshuo 
* @date: 2021/1/28 
*/
@SpringBootApplication
@ComponentScan(basePackages = {"com.renshuo"})
@EnableScheduling
@EnableFeignClients
public class ServiceWechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceWechatApplication.class, args);
	}

}
