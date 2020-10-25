package com.chstore.ca.ms.error;

public class CustomerServiceValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerServiceValidationException(final String message) {
		super(message);
	}
}