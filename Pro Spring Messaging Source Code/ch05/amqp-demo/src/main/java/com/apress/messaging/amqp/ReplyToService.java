package com.apress.messaging.amqp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

//@Component
public class ReplyToService {

	@RabbitListener(queues="${apress.amqp.queue}")
	@SendTo("${apress.amqp.reply-exchange-queue}")
	public Message<String> replyToProcess(String message){
		
		//More Processing here...
		
		return MessageBuilder
		        .withPayload("PROCESSED:OK")
		        .setHeader("PROCESSED", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
		        .setHeader("CODE", UUID.randomUUID().toString())
		        .build();
	}
	
}
