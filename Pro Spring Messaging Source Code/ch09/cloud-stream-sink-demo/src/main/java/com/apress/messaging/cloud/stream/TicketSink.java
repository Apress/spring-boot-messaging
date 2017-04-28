package com.apress.messaging.cloud.stream;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;

@EnableBinding(Sink.class)
public class TicketSink {
	
	/* Enable for Simple Log */
	/*
	@StreamListener(Sink.INPUT)
	public void process(Message<?> ticket){
		
	}
	*/
	
	
	
	/* Enable for Sink into RabbitMQ */
	
	@Bean
	public IntegrationFlow toAmqp(RabbitTemplate rabbitTemplate, @Value("${ticket.exchange:}") String exchange, @Value("${ticket.queue}") String queue){
		return IntegrationFlows
				.from(Sink.INPUT)
				.handle(Amqp.outboundAdapter(rabbitTemplate).exchangeName(exchange).routingKey(queue))
				.get();
	}
	
	@Bean
	public Queue rateQueue(@Value("${ticket.queue}") String queue){
		return new Queue(queue,true);
	}
}
