package com.apress.messaging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableConfigurationProperties(SpiProperties.class)
public class RateConfig {
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(new Jackson2JsonMessageConverter());
		return template;
	}
	
	@Bean
	public Queue rateQueue(@Value("${apress.spi.queue}") String queue){
		return new Queue(queue,true);
	}
	
	@Bean
	public MessageChannel amqpChannel() {
	    return MessageChannels.direct().get();
	}
	
	@Bean
	public IntegrationFlow rateFlow(RabbitTemplate rabbitTemplate, @Value("${apress.spi.exchange:}") String exchange, @Value("${apress.spi.queue}") String queue){
		return IntegrationFlows.from("amqpChannel")
				.handle(Amqp.outboundAdapter(rabbitTemplate).exchangeName(exchange).routingKey(queue))
				.get();
	}
	
	/* Enable to Consumer Messages using SPI */
	
	/*
	@Bean IntegrationFlow incomingRateFlow(ConnectionFactory connectionFactory, @Value("${apress.spi.queue}") String queue){
		return IntegrationFlows.from(Amqp.inboundAdapter(connectionFactory,queue).messageConverter(new Jackson2JsonMessageConverter()))
				.handle("simpleMessageHandler","process")
				.get();
	}
	*/
}
