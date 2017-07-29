package com.apress.messaging.config;

import java.io.File;

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
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.splitter.FileSplitter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import com.apress.messaging.domain.Rate;
import com.apress.messaging.integration.RateServiceActivator;

@Configuration
@EnableConfigurationProperties(RateSpiProperties.class)
public class RateSpiConfig {
	
	
	@Bean
	public MessageChannel defaultOutputChannel(){
		return MessageChannels.direct().get();
	}
	
	@Bean
	public MessageChannel toFileChannel(){
		return MessageChannels.direct().get();
	}
	
	@Bean
	public MessageChannel toAmqpChannel(){
		return MessageChannels.direct().get();
	}
	
	@Bean
	public IntegrationFlow fileToRateFlow(RateSpiProperties props, RateServiceActivator activator){
		return IntegrationFlows
				.from(Files
					.inboundAdapter(new File(props.getDirectory()))
					.preventDuplicates(true)
					.patternFilter(props.getFilePattern()), 
						e -> e.poller(Pollers.fixedDelay(5000L)))
			
				.split(Files.splitter().markers())
				.filter(p -> !(p instanceof FileSplitter.FileMarker))
				.transform(Transformers.fromJson(Rate.class))
				
				/* Enable for testing only. Making sure the File and content is read and is converted to a Rate object */
				//.handle(activator,"process")
				//.get();
				
				.routeToRecipients( r -> r.defaultOutputChannel(defaultOutputChannel())
										  .recipient("toFileChannel")
										  .recipient("toAmqpChannel"))
								
				.get();
	}
	
	@Bean
	public IntegrationFlow toFile(@Value("${rate.spi.process-directory}") String output){
		return IntegrationFlows
				.from("toFileChannel")
				.transform(Transformers.objectToString())
				.handle(Files.outboundAdapter(output)
							 .appendNewLine(true)
							 .fileExistsMode(FileExistsMode.APPEND)
							 .fileNameGenerator(n -> n.getHeaders().get(FileHeaders.FILENAME,String.class).replaceAll(".txt", ".processed"))
						     .autoCreateDirectory(true))
						     
				.get();
	}
	
	@Bean
	public IntegrationFlow toAmqp(RabbitTemplate rabbitTemplate, @Value("${rate.spi.exchange:}") String exchange, @Value("${rate.spi.queue}") String queue){
		return IntegrationFlows
				.from("toAmqpChannel")
				.handle(Amqp.outboundAdapter(rabbitTemplate).exchangeName(exchange).routingKey(queue))
				.get();
	}
	
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(new Jackson2JsonMessageConverter());
		return template;
	}
	
	@Bean
	public Queue rateQueue(@Value("${rate.spi.queue}") String queue){
		return new Queue(queue,true);
	}
	
}
