package com.ecommerce.exception;

/*
 * It is a Class annotated with @RestControllerAdvice annotation, is composed annotation that is annotated
 * with both @ControllerAdvice and @ResponseBody, which essentially means @ExceptionHandler methods are
 * rendered to the response body through message conversion (versus view resolution or template rendering).
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ecommerce.common.Common;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private final Common common;
    private final String description;

    public ControllerExceptionHandler(Common common) {
        this.common = common;
        this.description = common.getMessage("spring.application.name");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), common.getCurrentDay(), common.getMessage(ex.getMessage()),
                description);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage BadRequestException(BadRequestException ex, WebRequest request) {

        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), common.getCurrentDay(), common.getMessage(ex.getMessage()), description);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {

        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), common.getCurrentDay(),
                ex.getMessage(), description);
    }
}
