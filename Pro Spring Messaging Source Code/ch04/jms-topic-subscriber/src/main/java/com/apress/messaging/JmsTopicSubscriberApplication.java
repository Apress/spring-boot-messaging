package com.apress.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JmsTopicSubscriberApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsTopicSubscriberApplication.class, args);
	}
}
