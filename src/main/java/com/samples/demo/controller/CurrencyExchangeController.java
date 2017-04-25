package com.samples.demo.controller;

import java.util.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.concepts.domain.money.Money;
import com.samples.demo.service.BankingService;

@RestController
@RequestMapping("/api")
public class CurrencyExchangeController {
	
	 @Autowired
	 BankingService bankingService;
	 
	 @RequestMapping("/convert/{amount}/{fromCurrency}/{toCurrency}")
	 public Money convert(@PathVariable double amount, @PathVariable String fromCurrency, @PathVariable String toCurrency) {
		 Money sourceMoney = Money.valueOf(amount, Currency.getInstance(fromCurrency));
		 Currency targetCurrency = Currency.getInstance(toCurrency);
		 Money converted = bankingService.convertMoney(sourceMoney, targetCurrency);
		 return converted;
	 }
	 

}
