package com.ecommerce.exception;
/**
 * Exception created with the purpose of being launched upon validation error of the request.
 */
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String msg) {
		super(msg);
	}
}
