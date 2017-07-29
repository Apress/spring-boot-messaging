package com.apress.messaging.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientRate {
	
	@JsonProperty("code") //This is to Match the rate JSON object
	private String currencyCode;
	@JsonProperty("rate") //This is to Match the rate JSON object
	private Float currencyRate;
	@JsonIgnore
	private String source;
	@JsonProperty("date") //This is to Match the rate JSON object
	private Date updated;
		
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Float getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(Float currencyRate) {
		this.currencyRate = currencyRate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	

}
