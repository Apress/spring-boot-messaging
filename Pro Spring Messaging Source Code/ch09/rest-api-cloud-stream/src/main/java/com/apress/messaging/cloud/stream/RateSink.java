package com.apress.messaging.cloud.stream;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;

import com.apress.messaging.annotation.Log;
import com.apress.messaging.domain.Rate;

//@EnableBinding(RateExchange.class)
public class RateSink {

	@Log(printParamsValues=true)
	@ServiceActivator(inputChannel = RateExchange.OUTPUT_JPY)
	public void processJPY(Rate rate){
		
	}
	
	@Log(printParamsValues=true)
	@StreamListener(RateExchange.OUTPUT_EUR)
	public void processEUR(Rate rate){
		
	}
}
