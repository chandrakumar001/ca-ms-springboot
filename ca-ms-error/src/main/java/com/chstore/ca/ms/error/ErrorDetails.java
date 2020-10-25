package com.chstore.ca.ms.error;

import lombok.Value;

@Value
public class ErrorDetails {

	private String message;
	private String details;
	private String code;

	public static ErrorDetails of(String details, String message, String code) {
		return new ErrorDetails(message, details, code);
	}
}