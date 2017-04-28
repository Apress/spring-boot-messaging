package com.apress.messaging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

//@Configuration
public class SimpleAnnotationConfiguration {

	@Bean
	public MessageChannel input() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel toTransform() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel toLog() {
		return new DirectChannel();
	}

	
	/* Enable if you wat to use Java Configuration. Comment out the SimpleServiceActivator class */
	
	/*
	@Bean
	@ServiceActivator(inputChannel = "toLog")
	public LoggingHandler logging() {
		LoggingHandler adapter = new LoggingHandler(LoggingHandler.Level.INFO);
		adapter.setLoggerName("SIMPLE_LOGGER");
		adapter.setLogExpressionString("headers.id + ': ' + payload");
		return adapter;
	}
	*/
}

/* Enable them for the Java Config example to work */

/*
@MessageEndpoint
class SimpleFilter {
	@Filter(inputChannel = "input", outputChannel = "toTransform")
	public boolean process(String message) {
		return "World".equals(message);
	}
}

@MessageEndpoint
class SimpleTransformer {

	@Transformer(inputChannel = "toTransform", outputChannel = "toLog")
	public String process(String message) {
		return "Hello ".concat(message);
	}
}

@MessageEndpoint
class SimpleServiceActivator {

	@ServiceActivator(inputChannel = "toLog")
	public void process(String message) {

	}
}
*/
