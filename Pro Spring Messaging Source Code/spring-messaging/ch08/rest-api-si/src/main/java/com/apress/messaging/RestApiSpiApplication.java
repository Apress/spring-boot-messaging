package com.apress.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiSpiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiSpiApplication.class, args);
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
