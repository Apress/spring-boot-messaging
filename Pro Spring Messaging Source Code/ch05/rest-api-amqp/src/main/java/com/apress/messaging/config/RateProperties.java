package com.apress.messaging.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="rate.jms")
public class RateProperties {

	private String queueName;
	private String replyQueueName;

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getReplyQueueName() {
		return replyQueueName;
	}

	public void setReplyQueueName(String replyQueueName) {
		this.replyQueueName = replyQueueName;
	}
}
