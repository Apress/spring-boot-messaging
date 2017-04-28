package com.apress.messaging;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apress.messaging.config.JMSProperties;
import com.apress.messaging.jms.SimpleSender;

@SpringBootApplication
public class JmsSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsSenderApplication.class, args);
	}
	
	@Bean
	CommandLineRunner simple(JMSProperties props, SimpleSender sender){
		return args -> {
			sender.sendMessage(props.getQueue(), "Hello World");
		};
	}
	
	/* This code is for the Rates
	 
	@Bean
	CommandLineRunner process(JMSProperties props, RateSender sender){
		return args -> {
			sender.sendCurrency(props.getRateQueue(), new Rate("EUR",0.88857F,new Date()));
			sender.sendCurrency(props.getRateQueue(), new Rate("JPY",102.17F,new Date()));
			sender.sendCurrency(props.getRateQueue(), new Rate("MXN",19.232F,new Date()));
			sender.sendCurrency(props.getRateQueue(), new Rate("GBP",0.75705F,new Date()));
		};
	}
	
	*/
}
