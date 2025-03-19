package com.weddingplanner.exception;

public class EventNotFoundException extends RuntimeException {

    private Long eventId;

    public EventNotFoundException(Long eventId) {
        super("Event not found with ID: " + eventId);
        this.eventId = eventId;
    }
    
    public EventNotFoundException(String message) {
        super(message);
    }

    public Long getEventId() {
        return eventId;
    }
}
