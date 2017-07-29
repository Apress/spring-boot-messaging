package com.apress.messaging.controller;

import org.springframework.http.MediaType;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apress.messaging.domain.Person;
import com.apress.messaging.repository.PersonRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveController {

	SubscribableChannel personChannel;
	PersonRepository repo;
	
	public ReactiveController(SubscribableChannel personChannel, PersonRepository repo){
		this.personChannel = personChannel;
		this.repo = repo;
	}
	
	@GetMapping("/person")
	Flux<Person> list() {
		return Flux.fromStream(this.repo.getAll());
	}
	
	@GetMapping("/person/{id}")
	Mono<Person> findById(@PathVariable String id) {
		return Mono.just(this.repo.findOne(id));
	}
	
	@GetMapping(value="/person-watcher", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Person> log4Person(){
		return Flux.create( sink -> {
			MessageHandler handler = message -> sink.next((Person) message.getPayload());
			personChannel.subscribe(handler);
			sink.setCancellation(() -> personChannel.unsubscribe(handler));
		});
				
	}
	
	@PostMapping(value="/person", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void createPerson(@RequestBody Person person){
		if(person != null && person.getName() != null){
			repo.save(person);
			personChannel.send(MessageBuilder.withPayload(person).build());
		}
	}
	
}
