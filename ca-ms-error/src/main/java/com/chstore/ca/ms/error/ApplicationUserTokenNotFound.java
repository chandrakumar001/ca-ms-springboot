package com.chstore.ca.ms.error;

public class ApplicationUserTokenNotFound extends RuntimeException {
	public final String id;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationUserTokenNotFound(final String id) {
		this.id=id;
	}

}
