package com.apress.messaging.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component
public class AnnotatedConsumer {

	@RabbitListener(queues="${apress.amqp.queue}")
	public void process(String message){
		
	}
}
