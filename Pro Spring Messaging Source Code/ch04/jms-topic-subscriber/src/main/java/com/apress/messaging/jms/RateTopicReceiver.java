package com.apress.messaging.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.apress.messaging.domain.Rate;

@Component
public class RateTopicReceiver {

	@JmsListener(destination = "${apress.jms.topic}")
	public void processTopicRate(Rate rate){
		
	}
}
