package com.chstore.ca.ms.error;

public class BussinessServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BussinessServiceException(final String message){
		super(message);
	}
}
