package com.samples.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samples.demo.model.Greeting;

@RestController
public class HelloWorldRestController {
	
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam("type") String type, @RequestParam("name") String name) {
		Greeting greeting = new Greeting(type, name);
		return greeting;
	}

}
