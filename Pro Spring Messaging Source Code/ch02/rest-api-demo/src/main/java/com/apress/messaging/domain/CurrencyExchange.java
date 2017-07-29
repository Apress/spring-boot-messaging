package com.apress.messaging.domain;

public class CurrencyExchange {

	public static final String BASE_CODE = "USD";
	private String base;
	private String date;
	private Rate[] rates;
	
	public CurrencyExchange(String base, String date, Rate[] rates) {
		super();
		this.base = base;
		this.date = date;
		this.rates = rates;
	}
	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Rate[] getRates() {
		return rates;
	}
	public void setRates(Rate[] rates) {
		this.rates = rates;
	}
	
	
	
}
