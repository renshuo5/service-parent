package com.renshuo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages={"com.renshuo"})
public class ServiceWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceWebsocketApplication.class, args);
	}

}
