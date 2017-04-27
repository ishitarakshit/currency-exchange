package com.samples.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="No exchange rate available")
public class NoExchangeRateAvailableException extends RuntimeException {
	
	private static final long serialVersionUID = -1878185556277927412L;

	public NoExchangeRateAvailableException(String message) {
		super(message);
	}

}
