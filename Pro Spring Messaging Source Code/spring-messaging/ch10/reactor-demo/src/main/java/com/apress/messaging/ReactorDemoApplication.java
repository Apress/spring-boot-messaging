package com.apress.messaging;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apress.messaging.domain.Exchange;
import com.apress.messaging.service.ExchangeService;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactorDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(ReactorDemoApplication.class);
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(ReactorDemoApplication.class, args);
		System.in.read();
	}
	
	
	@Bean  
	CommandLineRunner reactorFlux(ExchangeService service){
		return args -> {
			
			log.info("Reactor >> Flux");
			Flux<Exchange> fluxExchage = service.getExchangeRates();
			
			// 1. Reactor's simple Flux
			fluxExchage
				.subscribe( ex -> log.info(ex.toString()) );
			
			
			
			// 2. Reactor's Schedulers
			/*
			fluxExchage
				.subscribeOn(Schedulers.parallel())
				.takeLast(10)
				.subscribe(ex -> log.info(ex.toString()));
			fluxExchage
				.subscribeOn(Schedulers.parallel())
				.subscribe(ex -> log.info(ex.toString()));
			*/
			
			// 3. Custom Executors/Schedulers
			/*
			ExecutorService executor = Executors.newFixedThreadPool(100);
			fluxExchage
				.subscribeOn(Schedulers.fromExecutor(executor))
				.subscribe(ex -> log.info(ex.toString()));
			
			executor.awaitTermination(1, TimeUnit.MINUTES);
			executor.shutdown();
			*/
		};
	}
}
