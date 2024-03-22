package com.renshuo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.renshuo"})
public class ServiceShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceShiroApplication.class, args);
	}

}
