package com.ecommerce.exception;

/**
 * A POJO class holding the information that be shown for Exception that be
 * thrown.
 */
public class ErrorMessage {

	private int statusCode;
	private String timestamp;
	private String message;
	private String description;

	public ErrorMessage(int statusCode, String string, String message, String description) {
		this.statusCode = statusCode;
		this.timestamp = string;
		this.message = message;
		this.description = description;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}
}
