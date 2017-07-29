package com.apress.messaging.redis;

import org.springframework.stereotype.Component;

import com.apress.messaging.domain.Rate;

@Component
public class RateRedisSubscriber {

	// If only one method defined, it must be named: handleMessage
	public void handleMessage(Rate rate) {
		// Process message here ...
	}
}
