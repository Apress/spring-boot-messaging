package com.apress.messaging;

import java.util.Random;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apress.messaging.domain.Person;
import com.apress.messaging.repository.PersonRepository;



@SpringBootApplication
public class SpringWebFluxReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebFluxReactiveApplication.class, args);
	}
	
	
	
	@Bean
	CommandLineRunner process(PersonRepository repo){
		return args -> {
			Stream
			.of("Laura", "Felipe", "Norma","Ximena", "Nayely", "Edgar", "Auristella", "Rocio", "Simon", "Elvira", "Antonio", "Gaby")
			.forEach( name -> repo.save(new Person(name,new Random().nextInt(40))));
			
			
			repo.findAll().forEach(System.out::println);
		};
	}
}



