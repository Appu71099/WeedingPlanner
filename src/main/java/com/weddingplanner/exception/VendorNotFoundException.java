package com.weddingplanner.exception;

public class VendorNotFoundException extends RuntimeException {

    public VendorNotFoundException(Long vendorId) {
        super("Vendor with ID " + vendorId + " not found");
    }

    public VendorNotFoundException(String message) {
        super(message);
    }

    public VendorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
