package com.apress.messaging.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SimpleSender {

	private JmsTemplate jmsTemplate;
	
	@Autowired
	public SimpleSender(JmsTemplate jmsTemplate){
		this.jmsTemplate = jmsTemplate;
	}
	
	public void sendMessage(String destination, String message){
		this.jmsTemplate.convertAndSend(destination, message);
	}
}
