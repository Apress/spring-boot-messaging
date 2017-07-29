package com.apress.messaging.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.support.Transformers;

@Configuration
@EnableConfigurationProperties(EmitterProperties.class)
public class EmitterConfig {
	
	private final String OUTPUT_CHANNEL = "processFileChannel";

	@Bean
    public IntegrationFlow fileReadingFlow(@Value("${emitter.directory}") String directory, @Value("${emitter.file-pattern}") String filePattern) {
         return IntegrationFlows
                  .from(s -> s.file(new File(directory))
                              .patternFilter(filePattern),
                          e -> e.poller(Pollers.fixedDelay(5000)))
                  .transform(Transformers.fileToString())
                  .channel(OUTPUT_CHANNEL)
                  .get();
        }
}
