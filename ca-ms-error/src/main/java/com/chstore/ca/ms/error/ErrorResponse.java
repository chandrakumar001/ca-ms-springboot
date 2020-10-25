package com.chstore.ca.ms.error;

import lombok.Value;

@Value
public class ErrorResponse {
	
	private ErrorDetails error;
	
	public static ErrorResponse of(final ErrorDetails error) {
		return new ErrorResponse(error);
	}
}
