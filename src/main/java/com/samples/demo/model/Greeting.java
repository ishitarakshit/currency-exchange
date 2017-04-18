package com.samples.demo.model;

public class Greeting {
	private final String type;
	private final String name;

	public Greeting(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

}
