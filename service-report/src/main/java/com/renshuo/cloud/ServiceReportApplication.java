package com.renshuo.cloud;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@ComponentScan(basePackages = {"com.renshuo"})
public class ServiceReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceReportApplication.class, args);
	}

}
