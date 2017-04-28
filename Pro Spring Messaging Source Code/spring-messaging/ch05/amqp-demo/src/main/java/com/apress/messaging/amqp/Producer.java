package com.apress.messaging.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	private RabbitTemplate template;
	
	@Autowired
	public Producer(RabbitTemplate template){
		this.template = template;
	}
	
	public void sendMessage(String exchange,String routingKey, String message){
		this.template.convertAndSend(exchange,routingKey, message);
	}
}
