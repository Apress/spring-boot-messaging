package com.apress.messaging;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apress.messaging.domain.Rate;
import com.apress.messaging.service.CurrencyService;

@SpringBootApplication
public class RestApiEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiEventsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner data(CurrencyService service) {
		return (args) -> {
			service.saveRate(new Rate("EUR",0.88857F,new Date()));
			service.saveRate(new Rate("JPY",102.17F,new Date()));
			service.saveRate(new Rate("MXN",19.232F,new Date()));
			service.saveRate(new Rate("GBP",0.75705F,new Date()));
		};
	}
}
