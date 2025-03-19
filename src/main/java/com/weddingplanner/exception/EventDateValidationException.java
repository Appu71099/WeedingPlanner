package com.weddingplanner.exception;

public class EventDateValidationException extends RuntimeException {

    public EventDateValidationException(String message) {
        super(message);
    }

    public EventDateValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
