package com.apress.messaging.cloud.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.router.AbstractMappingMessageRouter;
import org.springframework.integration.router.ExpressionEvaluatingRouter;

@EnableBinding(RateExchange.class)
public class RateProcessor {
	
	@Bean
	@ServiceActivator(inputChannel = RateExchange.INPUT)
	public AbstractMappingMessageRouter router(BinderAwareChannelResolver channelResolver) {
		AbstractMappingMessageRouter router = new ExpressionEvaluatingRouter(new SpelExpressionParser().parseExpression("payload.code"));
		router.setDefaultOutputChannelName("default-output");
		router.setChannelResolver(channelResolver);
		return router;
	}
	
}
