package com.apress.messaging.amqp;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class RpcClient {

	private RabbitTemplate template;

	@Autowired
	public RpcClient(RabbitTemplate template) {
		this.template = template;
	}

	public Object sendMessage(String exchange, String routingKey, String message) {
		Object response = this.template.convertSendAndReceive(exchange, routingKey, message);
		return response;
	}
}
