package com.samples.demo.service;

import java.time.LocalDate;
import java.util.Currency;

import com.concepts.domain.money.Money;
import com.samples.demo.exception.NoExchangeRateAvailableException;

public interface BankingService {

	Money convertMoney(Money from, Currency to) throws NoExchangeRateAvailableException;

	Money convertMoney(Money from, Currency to, LocalDate dayOfExchange) throws NoExchangeRateAvailableException;

	Money convertMoney(Money from, Currency to, Number exchangeRateOverride);

}
