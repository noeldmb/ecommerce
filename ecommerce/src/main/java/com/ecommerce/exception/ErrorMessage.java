package com.ecommerce.exception;

import lombok.Getter;

/*
 * A POJO class holding the information that be shown for Exception that be
 * thrown.
 */
@Getter
public class ErrorMessage {

	private final int statusCode;
	private final String timestamp;
	private final String message;
	private final String description;

	public ErrorMessage(int statusCode, String string, String message, String description) {
		this.statusCode = statusCode;
		this.timestamp = string;
		this.message = message;
		this.description = description;
	}

}
