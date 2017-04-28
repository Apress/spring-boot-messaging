package com.apress.messaging.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.apress.messaging.web.socket.LlWebSocketHandler;

@Configuration
@EnableWebSocket
public class LlWebSocketConfig implements WebSocketConfigurer{

	LlWebSocketHandler handler;
	
	public LlWebSocketConfig(LlWebSocketHandler handler){
		this.handler = handler;
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(this.handler, "/llws");
	}	
	
}
