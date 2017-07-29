package com.apress.messaging.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.apress.messaging.redis.Subscriber;

@Configuration
@EnableConfigurationProperties(SimpleRedisProperties.class)
public class RedisConfig {

	// Simple Message Listener
	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter, @Value("${apress.redis.topic}") String topic) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);		
		container.addMessageListener(listenerAdapter, new PatternTopic(topic));
		
		
		// Uncomment this out section if you need an extra subscriber
		/*
		container.addMessageListener(
				(message, pattern) ->{
				
					System.out.println("Pattern: " + new String(pattern));
					System.out.println("Message: " + message);
				
			}
		, new PatternTopic(topic));
		*/
		
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(Subscriber subscriber) {
		return new MessageListenerAdapter(subscriber);
	}
	
	
	/* This section is about using the JSON format Serialization.
	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter rateListenerAdapter, @Value("${apress.redis.rate}") String topic) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);		
		container.addMessageListener(rateListenerAdapter, new PatternTopic(topic));
		return container;
	}
	
	@Bean
	MessageListenerAdapter rateListenerAdapter(RateSubscriber subscriber) {
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(subscriber);
		messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(Rate.class));
		return messageListenerAdapter;
	}
	
	@Bean
	RedisTemplate<String, Rate> redisTemplate(RedisConnectionFactory connectionFactory){
		RedisTemplate<String,Rate> redisTemplate = new RedisTemplate<String,Rate>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Rate.class));
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
	*/
}
