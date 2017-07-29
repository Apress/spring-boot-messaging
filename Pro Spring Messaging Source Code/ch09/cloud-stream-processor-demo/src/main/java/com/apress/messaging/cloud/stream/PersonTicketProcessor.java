package com.apress.messaging.cloud.stream;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import com.apress.messaging.domain.Person;
import com.apress.messaging.domain.Ticket;

@EnableBinding(Processor.class)
public class PersonTicketProcessor {
	
	private List<String> movieTitles = Arrays.asList("Dead Pool 2","Pitch Perfect 3","Avatar 2","Tarzan 2","Cars 3"); 
	private Random rand = new Random();
	
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Ticket process(Person person) {
		Ticket ticket = new Ticket();
		ticket.setPerson(person);
		ticket.setMovieTitle(movieTitles.get(rand.nextInt(movieTitles.size())));
		ticket.setFreePopcorn(person.isFriend());
		ticket.setCost(person.isFriend() ? 0.0f: 15.75f);
		ticket.setValidUntil(new DateTime(new Date()).plusWeeks(1).toDate());
		
		return ticket;
	}

}
