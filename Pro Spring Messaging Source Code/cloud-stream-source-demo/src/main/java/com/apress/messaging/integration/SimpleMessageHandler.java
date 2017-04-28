package com.apress.messaging.integration;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageHandler {
	
	public void process(Message<?> message){
		
	}
	
	/* Enable this for a concrete Person Rate */
	/*
	public void process(Person message){
		
	}
	*/
	
	/* Enable this for a concrete Message Rate */
	/*
	public void process(Rate message){
		
	}
	*/
}
