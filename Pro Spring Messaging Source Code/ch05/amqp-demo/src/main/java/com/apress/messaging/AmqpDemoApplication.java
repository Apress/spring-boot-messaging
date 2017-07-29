package com.apress.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apress.messaging.amqp.Producer;

@SpringBootApplication
public class AmqpDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqpDemoApplication.class, args);
	}
	
	/* Uncomment out this for a Simple Producer */
	@Bean
	CommandLineRunner simple(@Value("${apress.amqp.exchange:}")String exchange, @Value("${apress.amqp.queue}")String routingKey, Producer producer){
		return args -> {
			producer.sendMessage(exchange, routingKey, "HELLO AMQP!");
		};
	}
	
	
	/* Uncomment out this for a RPC Client/Server 
	@Bean
	CommandLineRunner simpleRPC(@Value("${apress.amqp.exchange:}")String exchange, @Value("${apress.amqp.queue}")String routingKey, RpcClient client){
		return args -> {
			Object result = client.sendMessage(exchange, routingKey, "HELLO AMQP/RPC!");
			assert result!=null;
		};
	}
	*/
	
	/*
	@Bean
	CommandLineRunner process(AMQPProperties props, RateProducer producer){
		return args -> {
			producer.sendRate(props.getRateExchange(),props.getRateQueue(), new Rate("EUR",0.88857F,new Date()));
			producer.sendRate(props.getRateExchange(),props.getRateQueue(), new Rate("JPY",102.17F,new Date()));
			producer.sendRate(props.getRateExchange(),props.getRateQueue(), new Rate("MXN",19.232F,new Date()));
			producer.sendRate(props.getRateExchange(),props.getRateQueue(), new Rate("GBP",0.75705F,new Date()));
		};
	}
	*/
}
