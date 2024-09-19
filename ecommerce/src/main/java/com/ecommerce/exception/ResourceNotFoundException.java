package com.ecommerce.exception;

import java.io.Serial;

/*
 * Exception created with the purpose of being launched where not exist any information to be returned.
 */
public class ResourceNotFoundException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
