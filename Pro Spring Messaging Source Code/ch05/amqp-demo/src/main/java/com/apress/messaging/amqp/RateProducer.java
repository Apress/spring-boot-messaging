package com.apress.messaging.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.apress.messaging.domain.Rate;

//@Component
public class RateProducer {

	private RabbitTemplate template;

	@Autowired
	public RateProducer(RabbitTemplate template) {
		this.template = template;
	}

	public void sendRate(String exchange, String routingKey, Rate rate) {
		this.template.convertAndSend(exchange, routingKey, rate);
	}
}
