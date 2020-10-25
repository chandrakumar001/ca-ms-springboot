package com.chstore.ca.ms.error;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String CUSTOMER_ID_ALREADY_EXITING = "Customer id already exiting";

	public CustomerAlreadyExistsException(final String message) {
		super(CUSTOMER_ID_ALREADY_EXITING + message);
	}
}
