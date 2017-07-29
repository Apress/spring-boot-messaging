package com.apress.messaging;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apress.messaging.domain.Rate;
import com.apress.messaging.repository.RateRepository;

@SpringBootApplication
public class RestApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiDemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner data(RateRepository repository) {
		return (args) -> {
			repository.save(new Rate("EUR",0.88857F,new Date()));
			repository.save(new Rate("JPY",102.17F,new Date()));
			repository.save(new Rate("MXN",19.232F,new Date()));
			repository.save(new Rate("GBP",0.75705F,new Date()));
		};
	}
}
