package com.chstore.ca.ms.error;

public class ApplicationUserIDNotFound extends RuntimeException {
	public final String id;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationUserIDNotFound(final String id) {
		this.id=id;
	}

}
