package com.samples.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.concepts.domain.money.Money;
import com.samples.demo.config.AppConfig;
import com.samples.demo.exception.NoExchangeRateAvailableException;
import com.samples.demo.service.BankingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
public class CurrencyExchangeControllerTest {

	private static final double SRC_AMT = 50.00;
	private static final double CONVERTED_AMT = 2500.00;
	private static final String USD = "USD";
	private static final String INR = "INR";

	private MockMvc mockMvc;
	
	@Mock
	private BankingService bankingServiceMock;

	@InjectMocks
	private CurrencyExchangeController testController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
	}

	@Test
	public void testConvert() throws Exception {
		Money source = Money.valueOf(SRC_AMT, Currency.getInstance(USD));
		Currency target = Currency.getInstance(INR);
		Money converted = Money.valueOf(CONVERTED_AMT, target);
		
		when(bankingServiceMock.convertMoney(source, target)).thenReturn(converted);
		
		mockMvc.perform((get("/api/convert/" + SRC_AMT + "/" + USD + "/" + INR)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.amount").value(CONVERTED_AMT))
			.andExpect(jsonPath("$.currency").value(INR));
	}
	
	@Test
	public void testConvertWhenRatesAreNotAvailable() throws Exception {
		Money source = Money.valueOf(SRC_AMT, Currency.getInstance(USD));
		Currency target = Currency.getInstance(INR);
		
		when(bankingServiceMock.convertMoney(source, target)).thenThrow(NoExchangeRateAvailableException.class);
		
		mockMvc.perform((get("/api/convert/" + SRC_AMT + "/" + USD + "/" + INR)))
			.andExpect(status().isNotFound());
	}

}
