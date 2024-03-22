package com.renshuo.shardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.renshuo.shardingsphere.**.mapper")
public class ServiceShardingsphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceShardingsphereApplication.class, args);
	}

}
