package com.chstore.ca.ms.error;

public class CustomerServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	CustomerServiceException(final String message){
		super(message);
	}
}
