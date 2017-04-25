package com.samples.demo.exception;

public class NoExchangeRateAvailableException extends RuntimeException {
	
	private static final long serialVersionUID = -1878185556277927412L;

	public NoExchangeRateAvailableException(String message) {
		super(message);
	}

}
