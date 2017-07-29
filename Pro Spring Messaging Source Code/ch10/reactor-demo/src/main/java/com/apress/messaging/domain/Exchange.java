package com.apress.messaging.domain;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

public class Exchange {

	private String base = "USD";
	private SortedMap<String, Float> rates;
	private Date timestamp;

	public Exchange(SortedMap<String, Float> rates, Date timestamp) {
		this.rates = rates;
		this.timestamp = timestamp;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Map<String, Float> getRates() {
		return rates;
	}

	public void setRates(SortedMap<String, Float> rates) {
		this.rates = rates;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Exchange [base=" + base + ", rates=" + rates + ", timestamp=" + timestamp + "]";
	}

}
