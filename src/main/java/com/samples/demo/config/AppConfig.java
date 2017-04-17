package com.samples.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.samples.demo.controller.HelloWorldRestController;

@Configuration
@EnableWebMvc

public class AppConfig {
	@Bean
	public HelloWorldRestController helloWorldRestController(){
		return new HelloWorldRestController();
	}

}
