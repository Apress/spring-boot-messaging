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

import rx.Observable;

@Service
public class ExchangeService {
	private static final Logger log = LoggerFactory.getLogger(ExchangeService.class);
	private Exchange exchange;

	ExchangeService() {
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

	public Observable<Exchange> getExchangeRates() {
		return Observable.unsafeCreate(subscriber -> {
			
			while(!subscriber.isUnsubscribed()) {  //while (true) {
				
				try {
					Float factor = (new Random().nextFloat() * 2 - 1) / 10F;
					subscriber.onNext(new Exchange(exchange.getRates().entrySet().stream().collect(Collectors.toMap(
							entry -> entry.getKey(), entry -> entry.getValue() * factor + entry.getValue(), (v1, v2) -> {
								throw new RuntimeException(String.format("Duplicate key for values %s and %s", v1, v2));
							}, TreeMap::new)), new Date()));
					
					if (new Random().nextInt(100) > 90 )
						throw new Exception("Some values are getting too high!!");
					
					sleep(1000);
				} catch (Exception ex){
					log.error(ex.getMessage());
					
					//Uncomment this out if you need the subscriber.onError(Throwable t) method to be called.
					//subscriber.onError(ex);
				}
			}
		});

	}

	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception ex) {
		}
	}
}
