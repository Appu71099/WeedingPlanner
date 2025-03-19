package com.weddingplanner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.weddingplanner.exception.ClientNotFoundException;
import com.weddingplanner.exception.EventDateValidationException;
import com.weddingplanner.exception.EventNotFoundException;
import com.weddingplanner.exception.PaymentNotFoundException;
import com.weddingplanner.exception.VendorNotAvailableException;
import com.weddingplanner.exception.VendorNotFoundException;
import com.weddingplanner.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleClientNotFound(ClientNotFoundException ex) {
	    ErrorResponse errorResponse = new ErrorResponse("Client Not Found", ex.getMessage());
	    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

    @ExceptionHandler(VendorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVendorNotFound(VendorNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Vendor Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEventNotFoundException(EventNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Event Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePaymentNotFoundException(PaymentNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Payment Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(EventDateValidationException.class)
    public ResponseEntity<ErrorResponse> handleEventDateValidationException(EventDateValidationException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Event Date Not Valid", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(VendorNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleVendorNotAvailableException(VendorNotAvailableException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Vendor Not Availablable Please select other date", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}