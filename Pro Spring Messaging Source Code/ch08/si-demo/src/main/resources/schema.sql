DROP TABLE IF EXISTS person;

CREATE TABLE person (
	id integer NOT NULL AUTO_INCREMENT,
	first varchar(150),
	last varchar(150),
	dob date,
	phone varchar(12),
	email varchar(150),
	friend boolean,
	PRIMARY KEY(id)
);