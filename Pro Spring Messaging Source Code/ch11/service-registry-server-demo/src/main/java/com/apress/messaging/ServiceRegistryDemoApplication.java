package com.apress.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryDemoApplication.class, args);
	}
}
