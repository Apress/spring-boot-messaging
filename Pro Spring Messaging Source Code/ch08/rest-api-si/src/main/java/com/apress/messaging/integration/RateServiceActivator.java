package com.apress.messaging.integration;

import java.util.Map;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import com.apress.messaging.annotation.Log;
import com.apress.messaging.domain.Rate;

@Component
public class RateServiceActivator {

	@Log(printParamsValues=true)
	public void process(Rate rate, @Headers Map<String,String> headers){
		
	}
}
