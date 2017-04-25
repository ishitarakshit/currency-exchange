package com.samples.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import com.concepts.domain.time.CalendarDate;

public class ExchangeRate implements Comparable<ExchangeRate>, Serializable {

	private static final long serialVersionUID = 5195767437971583041L;
	private Currency source;
	private Currency target;
	private LocalDate exchangeDate;
	private BigDecimal rate;

	public ExchangeRate(Currency source, Currency target, BigDecimal rate, LocalDate exchangeDate) {
		this.source = source;
		this.target = target;
		this.rate = rate;
		this.exchangeDate = exchangeDate;
	}

	public Currency getSource() {
		return source;
	}

	public Currency getTarget() {
		return target;
	}

	public LocalDate getExchangeDate() {
		return exchangeDate;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public int compareTo(ExchangeRate other) {
		return other.getExchangeDate().compareTo(getExchangeDate()); // reverse
																		// sort
																		// order
	}

}
