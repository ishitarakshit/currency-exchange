package com.samples.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.samples.demo.config.AppConfig;
import com.samples.demo.config.security.SecurityJavaConfig;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, SecurityJavaConfig.class })
@WebAppConfiguration
public class CurrencyExchangeEndToEndTest {

	private static final double SRC_AMT = 10.00;
	private static final double CONVERTED_AMT = 470.00;
	private static final String USD = "USD";
	private static final String INR = "INR";

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}
	
	@Test
	public void testConvertWithValidUser() throws Exception {
		
		mockMvc.perform((get("/api/convert/" + SRC_AMT + "/" + USD + "/" + INR))
				.with(httpBasic("admin","admin")))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.amount").value(CONVERTED_AMT))
			.andExpect(jsonPath("$.currency").value(INR));
	}
	
	@Test
	public void testConvertWithInvalidUser() throws Exception {
		
		mockMvc.perform((get("/api/convert/" + SRC_AMT + "/" + USD + "/" + INR))
				.with(httpBasic("invalid","invalid")))
			.andExpect(status().isUnauthorized());
	}

}
