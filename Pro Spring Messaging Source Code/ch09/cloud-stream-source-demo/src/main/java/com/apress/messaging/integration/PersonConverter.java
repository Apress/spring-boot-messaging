package com.apress.messaging.integration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.apress.messaging.domain.Person;

@Component
public class PersonConverter implements Converter<String, Person> {

	@Override
	public Person convert(String str) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyy-mm-dd"); 
		List<String> fields = Stream.of(str.split(",")).map(String::trim).collect(Collectors.toList());
		try {
			return new Person(fields.get(0),fields.get(1),df.parse(fields.get(2)),fields.get(3),fields.get(4),Boolean.parseBoolean(fields.get(5)));
		} catch (ParseException e) {
			return null;
		}
	}

}
