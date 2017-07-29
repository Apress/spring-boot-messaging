package com.apress.messaging;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.integration.config.EnableIntegration;

/* Enable the following annotation to use the XML with the SpiFileToJdbcConfiguration examples */ 
//@ImportResource("META-INF/spring/integration/spi-file-to-jdbc.xml")

/* Enable the following annotation to use the XML configuration. Comment out all the @Configuration and @Component files. */ 
//@ImportResource({"META-INF/spring/integration/spi-context.xml"})

@EnableIntegration
@SpringBootApplication
public class SpiDemoApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpiDemoApplication.class)
		.web(true) // True for using a Web app and the /h2-console endpoint.
		.run(args);
	}
	
	/* Enable this for a Simple Example */
	
	/*
	@Bean
	CommandLineRunner process(MessageChannel input){
		return args -> {
			input.send(MessageBuilder.withPayload("World").build());
		};
	}
	*/
	
	
	/* Enable this for the Rate to RabbitMQ, make sure you have RabbitMQ up and running */
	
	/*
	@Bean
	CommandLineRunner processRate(MessageChannel amqpChannel){
		return args -> {
			amqpChannel.send(MessageBuilder.withPayload(new Rate("EUR",0.88857F,new Date())).build());
			amqpChannel.send(MessageBuilder.withPayload(new Rate("JPY",102.17F,new Date())).build());
			amqpChannel.send(MessageBuilder.withPayload(new Rate("MXN",19.232F,new Date())).build());
			amqpChannel.send(MessageBuilder.withPayload(new Rate("GBP",0.75705F,new Date())).build());
		};
	}
	*/
}
