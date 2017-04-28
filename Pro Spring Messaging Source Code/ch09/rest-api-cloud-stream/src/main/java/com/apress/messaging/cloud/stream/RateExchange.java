package com.apress.messaging.cloud.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface RateExchange {
	
	String INPUT = "rates";
	String OUTPUT_EUR = "EUR";
	String OUTPUT_JPY = "JPY";
	

	@Input(RateExchange.INPUT)
    SubscribableChannel rates();

    @Output(RateExchange.OUTPUT_EUR)
    MessageChannel eur();

    @Output(RateExchange.OUTPUT_JPY)
    MessageChannel jpy();
    
}
