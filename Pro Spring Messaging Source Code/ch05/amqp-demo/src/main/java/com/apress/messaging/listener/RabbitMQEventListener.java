package com.apress.messaging.listener;

import org.springframework.amqp.rabbit.listener.ListenerContainerConsumerFailedEvent;
import org.springframework.context.event.EventListener;

//@Component
public class RabbitMQEventListener {

	@EventListener
	public void handler(ListenerContainerConsumerFailedEvent container){

	}
}
