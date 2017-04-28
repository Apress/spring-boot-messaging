package com.apress.messaging.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.apress.messaging.domain.Person;
import com.apress.messaging.repository.PersonRepository;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PersonHandler {
	PersonRepository repo;
	EmitterProcessor<Person> stream = EmitterProcessor.<Person>create().connect();
	
	PersonHandler(PersonRepository repo) {
		this.repo = repo;
	}

	public Mono<ServerResponse> findAll(ServerRequest request) {
		Flux<Person> people = Flux.fromStream(this.repo.getAll());
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(people, Person.class);
	}

	public Mono<ServerResponse> findById(ServerRequest request) {
		String personId = request.pathVariable("id");
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<Person> personMono = Mono.just(this.repo.findOne(personId)); // Mono.fromFuture(this.repo.findById(personId));

		return personMono.then(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person)))
				.otherwiseIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> createPerson(ServerRequest request) { 
		Mono<Person> person = request.bodyToMono(Person.class);
		return ServerResponse.ok().build(person.doOnNext(p -> {
			this.repo.save(p);
			this.stream.onNext(p);
		}).then());
	}
	
	public Mono<ServerResponse> newPersonLog(ServerRequest request){
		Mono<Person> personMono = stream.doOnNext( person -> {
			System.out.println(">>>> [Person created] " + person);
		}).next().subscribe();
		personMono.block();
		return personMono.then(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person)));
	}
}
