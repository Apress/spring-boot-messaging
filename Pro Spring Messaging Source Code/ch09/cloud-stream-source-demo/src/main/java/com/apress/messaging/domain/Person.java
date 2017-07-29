package com.apress.messaging.domain;

import java.util.Date;

public class Person {

	private String first;
	private String last;
	private Date dob;
	private String phone;
	private String email;
	private boolean friend;
	
	
	public Person(){}

	public Person(String first, String last, Date dob, String phone, String email, boolean friend) {
		super();
		this.first = first;
		this.last = last;
		this.dob = dob;
		this.phone = phone;
		this.email = email;
		this.friend = friend;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isFriend() {
		return friend;
	}

	public void setFriend(boolean friend) {
		this.friend = friend;
	}

	@Override
	public String toString() {
		return "Person [first=" + first + ", last=" + last + ", dob=" + dob + ", phone=" + phone + ", email=" + email
				+ ", friend=" + friend + "]";
	}	
}
