package com.apress.messaging.jms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import com.apress.messaging.domain.Rate;

//@Component
public class RateReplyReceiver {

	
	@JmsListener(destination = "${apress.jms.rate-queue}")
	@SendTo("${apress.jms.rate-reply-queue}")
	public Message<String> processRate(Rate rate){
		
		//Process the Rate and return any significant value
		
		return MessageBuilder
				.withPayload("PROCCESSED")
				.setHeader("CODE", rate.getCode())
				.setHeader("RATE", rate.getRate())
				.setHeader("ID", UUID.randomUUID().toString())
				.setHeader("DATE", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
				.build();
	}
	
}
