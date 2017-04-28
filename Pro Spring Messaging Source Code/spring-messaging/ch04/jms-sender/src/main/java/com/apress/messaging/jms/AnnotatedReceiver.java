package com.apress.messaging.jms;

import org.springframework.jms.annotation.JmsListener;

//@Component
public class AnnotatedReceiver {

	@JmsListener(destination = "${apress.jms.queue}")
	public void processMessage(String content) {
	
	}

}
