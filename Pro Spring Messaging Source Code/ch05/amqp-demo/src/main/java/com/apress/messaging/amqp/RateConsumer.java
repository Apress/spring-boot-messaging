package com.apress.messaging.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.apress.messaging.domain.Rate;

//@Component
public class RateConsumer {

	@RabbitListener(queues="${apress.amqp.rate-queue}")
	public void messageHandler(Rate rate){
		
	}
	
}
