package com.apress.messaging.service;

import java.util.Date;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.apress.messaging.domain.Exchange;

import reactor.core.publisher.Flux;

@Service
public class ExchangeService {
	private static final Logger log = LoggerFactory.getLogger(ExchangeService.class);
	private Exchange exchange;

	ExchangeService() {
		log.info(">>> Exchange Service created.");
		@SuppressWarnings({ "serial" })
		SortedMap<String, Float> rates = new TreeMap<String, Float>() {
			{
				put("EUR", 0.942013F);
				put("JPY", 114.75440909F);
				put("MXN", 19.598225F);
				put("GPB", 0.819626F);
			}
		};
		exchange = new Exchange(rates, new Date());
	}

	public Flux<Exchange> getExchangeRates(){
		return Flux.create( sink -> {
			while(true){
				Float factor = (new Random().nextFloat() * 2 - 1) / 10F;
				sink.next(new Exchange(
						exchange.getRates().entrySet().stream().collect(Collectors.toMap(
							entry -> entry.getKey(), entry -> entry.getValue() * factor + entry.getValue(), (v1, v2) -> {
								throw new RuntimeException(String.format("Duplicate key for values %s and %s", v1, v2));
							}, TreeMap::new)), new Date()));
				
				sleep(1000);
				
				if(factor > 0.095F)
					sink.complete();
				
			}
		});
	}
	
	
	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception ex) { }
	}
}
