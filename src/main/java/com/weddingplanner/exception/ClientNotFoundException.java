package com.weddingplanner.exception;
public class ClientNotFoundException extends RuntimeException {

    private Long clientId;

    public ClientNotFoundException(Long clientId) {
        super("Client not found with ID: " + clientId);
        this.clientId = clientId;
    }

    public Long getClientId() {
        return clientId;
    }
}
