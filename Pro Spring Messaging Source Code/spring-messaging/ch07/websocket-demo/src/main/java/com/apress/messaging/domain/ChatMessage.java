package com.apress.messaging.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage {

	private String user;
	private String message;
	private Date sent;

	public ChatMessage() {
		this.sent = new Date();
	}

	public ChatMessage(String message) {
		this("anonymous", message);
	}

	public ChatMessage(String user, String message) {
		this.user = user;
		this.message = message;
		this.sent = new Date();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSent() {
		return sent;
	}

	public void setSent(Date sent) {
		this.sent = sent;
	}

	@Override
	public String toString() {
		return "ChatMessage [user=" + user + ", message=" + message + ", sent="
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sent) + "]";
	}

}
