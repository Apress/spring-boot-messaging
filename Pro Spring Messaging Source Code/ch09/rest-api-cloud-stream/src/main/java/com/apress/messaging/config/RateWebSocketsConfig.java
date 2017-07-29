package com.apress.messaging.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
@EnableConfigurationProperties(RateWebSocketsProperties.class)
public class RateWebSocketsConfig extends AbstractWebSocketMessageBrokerConfigurer{

	RateWebSocketsProperties props;
	
	public RateWebSocketsConfig(RateWebSocketsProperties props){
		this.props = props;
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(props.getEndpoint()).setAllowedOrigins("*").withSockJS();
		
	}

	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(props.getBroker());
        config.setApplicationDestinationPrefixes(props.getApp());
    }
}
