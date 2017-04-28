package com.apress.messaging.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.MediaType;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
public class SimpleEmmiterController {
	
	private final Map<String, SseEmitter> names = new ConcurrentHashMap<>();

	@GetMapping(value = "/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter getNames(){			  	
		SseEmitter sseEmitter = new SseEmitter(2 * 60 * 1000L); // 2 Minutes
		names.put("simpleEmitter",sseEmitter);
		return sseEmitter;
	}
	
	@ServiceActivator(inputChannel="processFileChannel")
	public void processNames(String payload){
		Random random = new Random();
		String[] allNames = payload.split("\n");
		random
			.ints(10, 0, allNames.length)
			.mapToObj( index -> allNames[index])
			.forEach( name -> {
					try {
						names.get("simpleEmitter").send(name);
						sleep(1000);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});
		
	}
	
	private void sleep(long milliseconds){
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) { }
	}
	
}
