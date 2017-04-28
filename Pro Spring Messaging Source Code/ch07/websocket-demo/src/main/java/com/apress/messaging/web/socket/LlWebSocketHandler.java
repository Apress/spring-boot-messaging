package com.apress.messaging.web.socket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class LlWebSocketHandler extends TextWebSocketHandler{

	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(">>>> " + message);
		
		//Enable this if you want to return the Message to the Client
		//session.sendMessage(message);
	}	
	
}
