package com.apress.messaging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;

//@Configuration
public class SpiSimpleConfiguration {

	
	/* You can add a declaration like the following or you can use @Component in the class */
	/*
	@Bean
	public SimpleMessageHandler simpleMessageHandler(){
		return new SimpleMessageHandler();
	}
	*/
	
	/* You have an option to configure the Channel as well */
	/*
	@Bean
	public MessageChannel input(){
		return MessageChannels.direct().get();
	}
	*/
	
	@Bean
	public IntegrationFlow simpleFlow(){
		return IntegrationFlows.from(MessageChannels.direct("input"))
				.filter("World"::equals)
				.transform("Hello "::concat)
				//.handle("simpleMessageHandler","process")  // You can uncomment this for using a handler. If so, comment out the .handle(System.out::println) statement.
				.handle(System.out::println)
				.get();
	}
	
}
