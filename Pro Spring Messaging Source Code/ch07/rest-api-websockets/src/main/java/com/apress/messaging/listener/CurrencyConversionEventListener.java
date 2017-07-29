package com.apress.messaging.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.apress.messaging.event.CurrencyConversionEvent;

@Component
public class CurrencyConversionEventListener implements ApplicationListener<CurrencyConversionEvent> {

	private static final String DASH_LINE = "===================================";
	private static final String NEXT_LINE = "\n";
	private static final Logger log = LoggerFactory.getLogger(CurrencyConversionEventListener.class);
	
	@Override
	public void onApplicationEvent(CurrencyConversionEvent event) {
		Object obj = event.getSource();
		StringBuilder str = new StringBuilder(NEXT_LINE);
		str.append(DASH_LINE);
		str.append(NEXT_LINE);
		str.append("  Class: " + obj.getClass().getSimpleName());
		str.append(NEXT_LINE);
		str.append("Message: " + event.getMessage());
		str.append(NEXT_LINE);
		str.append("  Value: " + event.getConversion());
		str.append(NEXT_LINE);
		str.append(DASH_LINE);
		log.error(str.toString());
	
	}

}
