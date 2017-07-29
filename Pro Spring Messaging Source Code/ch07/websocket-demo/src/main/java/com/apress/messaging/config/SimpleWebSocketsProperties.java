package com.apress.messaging.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "apress.ws")
public class SimpleWebSocketsProperties {

	private String appDestinationPrefix;
	private String endpoint;
	private String topic;
	private String mapping;
	private String mappingReply;
	private String rate;

	public String getMappingReply() {
		return mappingReply;
	}

	public void setMappingReply(String mappingReply) {
		this.mappingReply = mappingReply;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public String getAppDestinationPrefix() {
		return appDestinationPrefix;
	}

	public void setAppDestinationPrefix(String appDestinationPrefix) {
		this.appDestinationPrefix = appDestinationPrefix;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

}
