package com.samples.demo.repository;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Repository;

import com.samples.demo.model.ExchangeRate;

@Repository
public class ExchangeRateHashMapRepository implements ExchangeRateRepository {

    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency EUR = Currency.getInstance("EUR");
    private static final Currency INR = Currency.getInstance("INR");

    private HashMap<String, Set<ExchangeRate>> rates;

    public ExchangeRateHashMapRepository() {
        ExchangeRate rate1 = new ExchangeRate(USD, EUR, BigDecimal.valueOf(0.70), LocalDate.of(2015, 8, 22));
        ExchangeRate rate2 = new ExchangeRate(USD, EUR, BigDecimal.valueOf(0.75), LocalDate.of(2016, 8, 20));
        ExchangeRate rate3 = new ExchangeRate(USD, EUR, BigDecimal.valueOf(0.60), LocalDate.of(2017, 2, 19));
        Set<ExchangeRate> setUsdToEur = new TreeSet<ExchangeRate>();
        setUsdToEur.add(rate1);
        setUsdToEur.add(rate2);
        setUsdToEur.add(rate3);

        ExchangeRate rate4 = new ExchangeRate(USD, INR, BigDecimal.valueOf(45), LocalDate.of(2015, 8, 22));
        ExchangeRate rate5 = new ExchangeRate(USD, INR, BigDecimal.valueOf(46), LocalDate.of(2016, 8, 21));
        ExchangeRate rate6 = new ExchangeRate(USD, INR, BigDecimal.valueOf(47), LocalDate.of(2017, 4, 25));
        Set<ExchangeRate> setUsdToInr = new TreeSet<ExchangeRate>();
        setUsdToInr.add(rate4);
        setUsdToInr.add(rate5);
        setUsdToInr.add(rate6);
        
        rates = new HashMap<String, Set<ExchangeRate>>();
        rates.put("USD-EUR", setUsdToEur);
        rates.put("USD-INR", setUsdToInr);
    }

    public Set<ExchangeRate> getHistoricalExchangeRateData(Currency source, Currency target) {
        String rateLookupKey = getExchangeRateLookupKey(source, target);

        return rates.get(rateLookupKey);
    }

    private String getExchangeRateLookupKey(Currency source, Currency target) {
        return source.getCurrencyCode() + "-" + target.getCurrencyCode();
    }

}
