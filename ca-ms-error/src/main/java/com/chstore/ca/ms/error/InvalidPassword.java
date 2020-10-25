package com.chstore.ca.ms.error;

public class InvalidPassword extends RuntimeException {
	public final String id;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPassword(final String id) {
		this.id=id;
	}

}
