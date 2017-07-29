package com.apress.messaging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AMQPProperties.class)
public class AMQPConfig {

	/* Uncomment this out for a MessageListener implementation
	
	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListener consumer, @Value("${apress.amqp.queue}")String queueName) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(consumer);
		return container;
	}
	*/
	
	
	/* Uncomment this out for a RPC with a fixed reply-to queue 
	
	@Autowired
	ConnectionFactory connectionFactory;
	
	@Value("${apress.amqp.reply-queue}")
	String replyQueueName;
		
	@Bean
	public RabbitTemplate fixedReplyQueueRabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setReplyAddress(replyQueueName);
		template.setReplyTimeout(60000L);
		return template;
	}
	
	@Bean
	public SimpleMessageListenerContainer replyListenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueues(replyQueue());
		container.setMessageListener(fixedReplyQueueRabbitTemplate());
		return container;
	}
	
	@Bean
	public Queue replyQueue(){
		return new Queue(replyQueueName,false);
	}
	*/
	
	
	//Converters
	
	/*
	//Producer
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(new Jackson2JsonMessageConverter());
		return template;
	}
	
	//Consumer
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
	    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	    factory.setConnectionFactory(connectionFactory);
	    factory.setMessageConverter(new Jackson2JsonMessageConverter());
	    return factory; 
	}
	*/
	
	//Queues
	
	
	@Bean
	public Queue queue(@Value("${apress.amqp.queue}")String queueName){
		return new Queue(queueName,false);
	}
	
	
	/*
	@Bean
	public Queue rateQueue(@Value("${apress.amqp.rate-queue}")String queueName){
		return new Queue(queueName,false);
	}
	*/
	
	
	// Template with Blocked/UnBlocked/Shutdown Listeners
	
	/*
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.execute(new ChannelCallback<Object>() {

			public Object doInRabbit(Channel channel) throws Exception {
			
				channel.getConnection().addShutdownListener(new ShutdownListener() {
					public void shutdownCompleted(ShutdownSignalException cause) {
						// Process the shutdown
					}
				});
				
				channel.getConnection().addBlockedListener(new BlockedListener() {
					
					public void handleUnblocked() throws IOException {
						// Resume business logic
					}
					
					@Override
					public void handleBlocked(String reason) throws IOException {
						// FlowControl -> Logic to handle block 
					}
				});
				
				return null;
			}
			
		});
		return template;
	}
	*/
	
	// Template with ShutdownListener and Java 8 Lambda
	/*	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.execute( channel -> {
			channel.getConnection().addShutdownListener( cause -> {
				
				//Logic Here
				
			});
			return null;
		});
		
		return template;
	}
	*/
	
	
	// Retry for the consumer, normally this needs to be set in the container:  
	// container.setAdviceChain(new Advice[] { interceptor() });
	// 
	/*
	@Bean
	public StatefulRetryOperationsInterceptor interceptor() {
		return RetryInterceptorBuilder.stateful()
				.maxAttempts(3)
				.backOffOptions(1000, 2.0, 10000) // initialInterval, multiplier, maxInterval
				.build();
	}
	*/
	
	
	// Retry for the Consumer. After the 3 attempts, the next will be the RepublishMessageRecoverer
	/*
	@Bean
	RetryOperationsInterceptor interceptor(RabbitTemplate template,@Value("${apress.amqp.error-exchange:}")String errorExchange, @Value("${apress.amqp.error-routing-key}")String errorExchangeRountingKey) {
		return RetryInterceptorBuilder.stateless()
				.maxAttempts(3)
				.recoverer(new RepublishMessageRecoverer(template, errorExchange, errorExchangeRountingKey))
				.build();
	}
	*/
}
