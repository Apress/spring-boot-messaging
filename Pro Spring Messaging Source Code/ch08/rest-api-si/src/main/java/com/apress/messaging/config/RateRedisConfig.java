package com.apress.messaging.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.apress.messaging.domain.Rate;
import com.apress.messaging.redis.RateRedisSubscriber;

@Configuration
@EnableConfigurationProperties(RateRedisProperties.class)
public class RateRedisConfig {

	@ConditionalOnProperty("rate.redis.topic") // This is useful when there is no provided properties in this case the rate.redis.topic.
	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter rateListenerAdapter, @Value("${rate.redis.topic}") String topic) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);		
		container.addMessageListener(rateListenerAdapter, new PatternTopic(topic));
		return container;
	}
	
	@Bean
	MessageListenerAdapter rateListenerAdapter(RateRedisSubscriber subscriber) {
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
}
