package com.samples.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {
	
	@RequestMapping("/greeting")
	public String greeting() {
		return "Hello World!";
	}

}
