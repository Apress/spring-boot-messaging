package com.apress.messaging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.SubscribableChannel;

@Configuration
public class ReactiveConfig {

	@Bean
	public SubscribableChannel personChannel(){
		return MessageChannels.publishSubscribe().get();
	}
}
