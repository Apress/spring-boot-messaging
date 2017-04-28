package com.apress.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class RxJavaDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(RxJavaDemoApplication.class);
	public static void main(String[] args){
		 log.info("Demo Steam Application");
		 new SpringApplicationBuilder(RxJavaDemoApplication.class)
		            .web(false)
	                .run(args);
		
	}
	
	/* Simple Observable. A subscriber that never ends... */
	/*
	@Bean  
	CommandLineRunner rxJava(ExchangeService service){
		return args -> {
			
			log.info("RxJava >> Observable");
			
			Observable<Exchange> exchange = service.getExchangeRates();
			exchange	.subscribe(System.out::println);
			
		};
	}
	*/
	
	/* A more complete Example */	
	/*
	@Bean  
	CommandLineRunner rxJava(ExchangeService service){
		return args -> {
			Observable<Exchange> observableExchange = service.getExchangeRates();
			observableExchange.subscribe(new Subscriber<Exchange>(){

				@Override
				public void onCompleted() {
					log.info("EXCHANGE COMPLETED!!");
				}

				@Override
				public void onError(Throwable t) {
					log.error("EXCHANGE IS SKYROCKETING... >> " + t.getMessage());	
					unsubscribe();
				}

				@Override
				public void onNext(Exchange ex) {
					log.info(ex.toString());
					
					// Unsubscribe
					if(ex.getRates().get("JPY").floatValue() > 125.0F){
						log.warn(">>> JPY rate is now to high: " + ex.getRates().get("JPY").floatValue() + ", is time to quit. Bye.");
						unsubscribe();
					}
				}});
			
			//If needed, you can resume getting values 
			
			//observableExchange.onErrorResumeNext( t -> {
			//	   		log.info("Keep an eye the rates ... ");
			//	   		log.warn(t.getMessage());
			//	   		return service.getExchangeRates();
			//       })
			//  	.subscribe(ex -> { log.info(ex.toString()); }, t -> log.error(t.getMessage()));
			
			
		};
	}
	*/
	
	
	/* Another way to interact with only a few Exchange data */
	/*
	@Bean  
	CommandLineRunner rxJava(ExchangeService service){
		return args -> {
			Observable<Exchange> observableExchange = service.getExchangeRates();
			observableExchange.skipWhile(ex -> ex.getRates().get("EUR").floatValue() < 0.95F)
				.take(10)
				.subscribe(ex -> { log.info(ex.toString()); }, t -> log.error(t.getMessage()));
		};
	}
	*/
	
	
	/* Async Observables */
	/* 
	@Bean  
	CommandLineRunner rxJava(ExchangeService service){
		return args -> {
			ExecutorService executorService = Executors.newFixedThreadPool(100);
			
			Observable<Exchange> observableExchange = service.getExchangeRates();
			
			observableExchange
				.take(10)
				.subscribeOn(Schedulers.from(executorService))
				.forEach(ex -> { log.info(ex.toString()); });
			
			observableExchange
				.take(10)
				.subscribeOn(Schedulers.from(executorService))
				.forEach(ex -> { log.info(ex.toString()); });
			
			log.info(">> ASYNC Observanbles");
			executorService.awaitTermination(1, TimeUnit.MINUTES);
		    executorService.shutdown();
		    
		};
	}
	*/
}
