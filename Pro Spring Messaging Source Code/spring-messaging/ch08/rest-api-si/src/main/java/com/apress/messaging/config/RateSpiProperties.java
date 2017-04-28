package com.apress.messaging.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rate.spi")
public class RateSpiProperties {

	private String directory;
	private String filePattern;
	private String processDirectory = "./processed/";
	private String exchange = "";
	private String queue = "spi.rate";

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getFilePattern() {
		return filePattern;
	}

	public void setFilePattern(String filePattern) {
		this.filePattern = filePattern;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getProcessDirectory() {
		return processDirectory;
	}

	public void setProcessDirectory(String processDirectory) {
		this.processDirectory = processDirectory;
	}

}
