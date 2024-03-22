package com.renshuo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.renshuo.cloud")
public class ServiceGatewaySecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceGatewaySecurityApplication.class, args);
	}

}
