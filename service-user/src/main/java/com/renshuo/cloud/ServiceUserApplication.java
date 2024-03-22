package com.renshuo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.renshuo"})
@EnableSwagger2
@EnableFeignClients
public class ServiceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceUserApplication.class, args);
		System.out.println("        ヾ(◍°∇°◍)ﾉﾞ    Application启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
				"                    _\n" +
				"            "
				+ "      _(_)_                           wWWWw   _\n" +
				"      @@@@       (_)@(_)   vVVVv     _     @@@@  (___) _(_)_\n" +
				"     @@()@@ wWWWw  (_)\\    (___)   _(_)_  @@()@@   Y  (_)@(_)\n" +
				"      @@@@  (___)     `|/    Y    (_)@(_)  @@@@   \\|/   (_)\\\n" +
				"       /      Y       \\|    \\|/    /(_)    \\|      |/      |\n" +
				"    \\ |     \\ |/       | / \\ | /  \\|/       |/    \\|      \\|/\n" +
				"    \\\\|//   \\\\|///  \\\\\\|//\\\\\\|/// \\|///  \\\\\\|//  \\\\|//  \\\\\\|// \n" +
				"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

	}

}
