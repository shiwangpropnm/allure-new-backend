package com.allure.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Interests {
	male("male"),
	female("female"),
	all("all");
	
	String value;
}
