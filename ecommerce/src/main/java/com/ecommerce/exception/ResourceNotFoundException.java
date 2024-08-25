package com.ecommerce.exception;
/**
 * Exception created with the purpose of being launched where not exist any information to be returned.
 */
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
