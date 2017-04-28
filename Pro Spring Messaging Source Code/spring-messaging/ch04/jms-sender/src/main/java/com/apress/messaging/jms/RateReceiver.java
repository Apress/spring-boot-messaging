package com.apress.messaging.jms;

import org.springframework.jms.annotation.JmsListener;

import com.apress.messaging.domain.Rate;

//@Component
public class RateReceiver {

	@JmsListener(destination = "${apress.jms.rate-queue}")
	public void processRate(Rate rate){
		
	}
}
