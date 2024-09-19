package com.ecommerce.exception;

import java.io.Serial;

/*
 * Exception created with the purpose of being launched upon validation error of the request.
 */
public class BadRequestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException(String msg) {
        super(msg);
    }
}
