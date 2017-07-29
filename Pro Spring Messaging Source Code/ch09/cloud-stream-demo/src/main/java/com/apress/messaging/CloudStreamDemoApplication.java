package com.apress.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudStreamDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStreamDemoApplication.class, args);
	}
	
	/* Enable this for the Processor and Sink example. A programmatic way to send messages to the input channel */
	/*
	@Bean
	CommandLineRunner sourceSender(MessageChannel input){
		return args ->{
			input.send(MessageBuilder.withPayload("hello world").build());
		};
	}
	*/
	
	
	
	/* This is another way to send a message using a source, another way to start the communication 
	   If you want to use this method, you need to setup the destinations in the application.properties:
	   
	   spring.cloud.stream.bindings.input.destination=simple-message
	   spring.cloud.stream.bindings.output.destination=simple-message
	   
	   and add to this class the @EnableBinding(Source.class), this will connect the Source and the Sink */
	   
	/*
	@Bean
	CommandLineRunner sourceSender(Source source){
		return args ->{
			source.output().send(MessageBuilder.withPayload("hello Sink").build());
		};
	}
	*/
	
	
}
