package com.chstore.ca.ms.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonExceptionBuilder {

	public static ResponseEntity<ErrorResponse> build(final HttpStatus httpStatusCode, final Exception e,String code) {

		final ErrorDetails errorDetails = ErrorDetails.of(httpStatusCode.getReasonPhrase(), e.getMessage(), code);
		final ErrorResponse errorResponse = ErrorResponse.of(errorDetails);
		return ResponseEntity.status(httpStatusCode).body(errorResponse);
	}
}
