package com.apress.messaging.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.apress.messaging.domain.ChatMessage;

@Controller
public class SimpleController {

	@MessageMapping("${apress.ws.mapping}")
	@SendTo("/topic/chat-room")
	public ChatMessage chatRoom(ChatMessage message) {
		return message;
	}
	
	
	/* Enable for a dynamic destination */
	/*
	@MessageMapping("${apress.ws.mapping}/{room}")
	@SendTo("/topic/chat-room/{user}")
	public ChatMessage directChatRoom(@DestinationVariable("room")String room
			,@DestinationVariable("user")String user,ChatMessage message) {
		
		return message;
	}
	*/
}
