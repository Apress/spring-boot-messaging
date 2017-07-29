package com.apress.messaging.config;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.file.splitter.FileSplitter;

import com.apress.messaging.integration.PersonConverter;

/* You can enable the next statement here or in the main application, not in both*/
//@ImportResource("META-INF/spring/integration/spi-file-to-jdbc.xml")

//@Configuration
//@EnableConfigurationProperties(SpiProperties.class)
public class SpiFileToJdbcConfigutation {

	private SpiProperties props;
	private PersonConverter personConverter;
	
	public SpiFileToJdbcConfigutation(SpiProperties props, PersonConverter personConverter){
		this.props = props;
		this.personConverter = personConverter;
	}
	
	@Bean
	public IntegrationFlow fileToJdbcFlow(){
		return IntegrationFlows.from(Files
				.inboundAdapter(new File(this.props.getDirectory()))
					.preventDuplicates(true)
					.patternFilter(this.props.getFilePattern()), 
							e -> e.poller(Pollers.fixedDelay(5000L)))
				
				.split(Files.splitter().markers())
				.filter(p -> !(p instanceof FileSplitter.FileMarker))
				.transform(Transformers.converter(personConverter))
				.filter("payload.isFriend()")
				.channel("input")
				.get();
	}
	
}
