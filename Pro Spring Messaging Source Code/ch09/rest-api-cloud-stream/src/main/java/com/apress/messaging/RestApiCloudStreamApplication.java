package com.apress.messaging;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.MessageBuilder;

import com.apress.messaging.domain.Rate;

@EnableBinding(Source.class)
@SpringBootApplication
public class RestApiCloudStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiCloudStreamApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner messageing(Source source) {
		return (args) -> {
			source.output().send(MessageBuilder.withPayload(new Rate("EUR",0.88857F,new Date())).build());
			source.output().send(MessageBuilder.withPayload(new Rate("JPY",102.17F,new Date())).build());
			source.output().send(MessageBuilder.withPayload(new Rate("JPY",114.02F,new Date())).build());
			source.output().send(MessageBuilder.withPayload(new Rate("MXN",19.232F,new Date())).build());
			source.output().send(MessageBuilder.withPayload(new Rate("GBP",0.75705F,new Date())).build());
			source.output().send(MessageBuilder.withPayload(new Rate("EUR",0.94F,new Date())).build());
			source.output().send(MessageBuilder.withPayload(new Rate("JPY",107.13F,new Date())).build());
		};
	}
	
	
	/*
	@Bean
	public CommandLineRunner data(RateRepository repository) {
		return (args) -> {
			repository.save(new Rate("EUR",0.88857F,new Date()));
			repository.save(new Rate("JPY",102.17F,new Date()));
			repository.save(new Rate("MXN",19.232F,new Date()));
			repository.save(new Rate("GBP",0.75705F,new Date()));
		};
	}
	*/
}
