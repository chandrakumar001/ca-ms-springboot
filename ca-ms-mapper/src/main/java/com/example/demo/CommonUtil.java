package com.example.demo;

public class CommonUtil {

	public static boolean requireNonNull(final String input) {
		return input == null || input.isEmpty() || input.trim().length() == 0;
	}
}
