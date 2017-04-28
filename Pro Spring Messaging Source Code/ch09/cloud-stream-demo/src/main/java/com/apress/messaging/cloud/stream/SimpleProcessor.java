package com.apress.messaging.cloud.stream;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

//@EnableBinding(Processor.class)
public class SimpleProcessor {

	
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public String transformToUpperCase(String message) {
		return message.toUpperCase();
	}
	
	
	/* You can use either @StreamListener and @SendTo (together) or just the @Transformer 
	   In this example the method uses the generic Message<?> abother way to consumer messages */
	
	/*
	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public String transformToUpperCase(Message<?> message) {
		if(message.getPayload() instanceof byte[])
			return new String((byte[])message.getPayload()).toUpperCase();
		
		return "SORRY, I don't know how to use this type: " + message.getPayload().getClass().getName();
	}
	*/
}
