package com.samples.demo.repository;

import java.util.Currency;
import java.util.Set;

import com.samples.demo.model.ExchangeRate;

public interface ExchangeRateRepository {

	Set<ExchangeRate> getHistoricalExchangeRateData(Currency source, Currency target);
}
