package com.apress.messaging.cloud.stream;

import java.io.File;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.file.splitter.FileSplitter;

import com.apress.messaging.integration.PersonConverter;

@EnableBinding(Source.class)
public class PersonFileSource {
	
	private PersonFileProperties props;
	private PersonConverter personConverter;
	
	public PersonFileSource(PersonFileProperties props, PersonConverter personConverter){
		this.props = props;
		this.personConverter = personConverter;
	}
	
	@Bean
	public IntegrationFlow fileFlow(){
		return IntegrationFlows.from(Files
				.inboundAdapter(new File(this.props.getDirectory()))
					.preventDuplicates(true)
					.patternFilter(this.props.getNamePattern()), 
							e -> e.poller(Pollers.fixedDelay(5000L)))
				
				.split(Files.splitter().markers())
				.filter(p -> !(p instanceof FileSplitter.FileMarker))
				.transform(Transformers.converter(personConverter))
				.channel(Source.OUTPUT)
				.get();
	}
}
