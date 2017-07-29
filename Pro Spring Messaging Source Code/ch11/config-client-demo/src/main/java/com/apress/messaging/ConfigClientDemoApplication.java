package com.apress.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ConfigClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientDemoApplication.class, args);
	}
	
	// This variable must be in the Github repository in the simple-config-client.properties file.
	@Value("${hello-world-message}") 
	String message;
	
	@GetMapping("/")
	public String famousHelloWorld(){
		return message;
	}
}
