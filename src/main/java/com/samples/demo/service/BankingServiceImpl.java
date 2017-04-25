package com.samples.demo.service;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concepts.domain.money.Money;
import com.samples.demo.exception.NoExchangeRateAvailableException;
import com.samples.demo.model.ExchangeRate;
import com.samples.demo.repository.ExchangeRateRepository;

@Service
public class BankingServiceImpl implements BankingService {
	
	@Autowired
	private ExchangeRateRepository exchangeRateRepository;

	@Override
	public Money convertMoney(Money moneyInSourceCurrency, Currency targetCurrency) throws NoExchangeRateAvailableException {
		LocalDate today = LocalDate.now();
		return convertMoney(moneyInSourceCurrency, targetCurrency, today);
	}

	@Override
	public Money convertMoney(Money moneyInSourceCurrency, Currency targetCurrency, LocalDate dayOfExchange) throws NoExchangeRateAvailableException {
		Currency sourceCurrency = moneyInSourceCurrency.getCurrency();

		Number exchangeRate = getExchangeRate(sourceCurrency, targetCurrency, dayOfExchange);

		return convertMoney(moneyInSourceCurrency, targetCurrency, exchangeRate);
	}

	@Override
	public Money convertMoney(Money moneyInSourceCurrency, Currency targetCurrency, Number exchangeRateOverride) {
		double convertedAmount = moneyInSourceCurrency.getAmount().doubleValue() * exchangeRateOverride.doubleValue();

		Money moneyInTargetCurrency = Money.valueOf(convertedAmount, targetCurrency);

		return moneyInTargetCurrency;
	}
	
	private Number getExchangeRate(Currency sourceCurrency, Currency targetCurrency, LocalDate exchangeDate) {
		if (sourceCurrency.equals(targetCurrency)) {
			return 1;
		}

		Set<ExchangeRate> rates = exchangeRateRepository.getHistoricalExchangeRateData(sourceCurrency, targetCurrency);
		if (rates != null && !rates.isEmpty()) {
			return getEffectiveExchangeRate(exchangeDate, rates);
		}

		Set<ExchangeRate> inverseRates = exchangeRateRepository.getHistoricalExchangeRateData(targetCurrency,
				sourceCurrency);
		if (inverseRates != null && !inverseRates.isEmpty()) {
			return 1 / getEffectiveExchangeRate(exchangeDate, inverseRates).doubleValue();
		}

		throw new NoExchangeRateAvailableException("No exchange rate available for " + sourceCurrency.getCurrencyCode()
				+ " to " + targetCurrency.getCurrencyCode());
	}

	private Number getEffectiveExchangeRate(LocalDate exchangeDate, Set<ExchangeRate> rates) {
		for (ExchangeRate thisRate : rates) {
			if (thisRate.getExchangeDate().equals(exchangeDate) || thisRate.getExchangeDate().isBefore(exchangeDate)) {
				return thisRate.getRate();
			}
		}

		throw new NoExchangeRateAvailableException("No exchange rate available for exchange date " + exchangeDate);
	}

}
