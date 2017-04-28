package com.apress.messaging.cloud.stream;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

//@EnableBinding(Sink.class)
public class SimpleSink {

	
	@StreamListener(Sink.INPUT)
	public void process(String message){
		
	}
	
}
