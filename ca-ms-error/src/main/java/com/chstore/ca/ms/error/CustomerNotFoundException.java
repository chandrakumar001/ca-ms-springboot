package com.chstore.ca.ms.error;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerNotFoundException extends RuntimeException {

	private static final String CUSTOMER_ID_NOT_FOUND = "Customer Id not found: ";
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(final String message) {
		super(CUSTOMER_ID_NOT_FOUND+message);
	}
}
