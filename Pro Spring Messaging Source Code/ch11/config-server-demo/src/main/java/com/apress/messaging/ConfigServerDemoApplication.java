package com.apress.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerDemoApplication.class, args);
	}
}
