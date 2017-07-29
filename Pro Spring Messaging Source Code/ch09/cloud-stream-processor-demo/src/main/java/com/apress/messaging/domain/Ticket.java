package com.apress.messaging.domain;

import java.util.Date;
import java.util.UUID;

public class Ticket {

	private String id;
	private Person person;
	private String movieTitle;
	private Boolean freePopcorn;
	private float cost = 12.00f;
	private Date validUntil;

	public Ticket() {
		this.id = UUID.randomUUID().toString();
	}

	public Ticket(String id, Person person, String movieTitle, Boolean freePopcorn, float cost) {
		this();
		this.person = person;
		this.movieTitle = movieTitle;
		this.freePopcorn = freePopcorn;
		this.cost = cost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public Boolean getFreePopcorn() {
		return freePopcorn;
	}

	public void setFreePopcorn(Boolean freePopcorn) {
		this.freePopcorn = freePopcorn;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

}
