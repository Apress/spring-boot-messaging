package com.apress.messaging.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.apress.messaging.annotation.Log;
import com.apress.messaging.domain.Rate;
import com.apress.messaging.service.CurrencyService;

@Component
public class RateJmsReceiver {

	private CurrencyService service;
	
	@Autowired
	public RateJmsReceiver(CurrencyService service){
		this.service = service;
	}
	
	/* For a simple Receiver, no reply */
	@JmsListener(destination = "${rate.jms.queue-name}")
	@Log(printParamsValues=true)
	public void processRate(Rate rate){
		this.service.saveRate(rate);
	}
	
	/*
	@JmsListener(destination = "${rate.jms.queue-name}")
	@SendTo("${rate.jms.reply-queue-name}")
	@Log(printParamsValues=true)
	public Message<String> processRateWithReply(Rate rate){
		this.service.saveRate(rate);
		return MessageBuilder
				.withPayload("PROCCESSED")
				.setHeader("DATE", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
				.build();
	}
	*/
}
