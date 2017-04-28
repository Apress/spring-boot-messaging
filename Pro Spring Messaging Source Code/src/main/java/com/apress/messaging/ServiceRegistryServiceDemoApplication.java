package com.apress.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceRegistryServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryServiceDemoApplication.class, args);
	}
	
	@GetMapping("/message")
	public String getMessage(){
		return "Hello World from a Service Discovery";
	}
}
