package com.example.iozzspringbootlz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;


public class NewStudent {

	final String name;

	final String number;

	final String grupa;
	@JsonCreator
	public NewStudent(@JsonProperty("name") String name, @JsonProperty("number") String number, @JsonProperty("group") String grupa) {
		this.name = name;
		this.number = number;
		this.grupa = grupa;
	}
}
