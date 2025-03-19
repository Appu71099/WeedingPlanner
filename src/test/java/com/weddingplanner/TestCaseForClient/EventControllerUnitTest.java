package com.weddingplanner.TestCaseForClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.weddingplanner.controller.EventController;
import com.weddingplanner.model.Event;
import com.weddingplanner.service.EventService;

public class EventControllerUnitTest {

    @Mock
    private EventService eventService; 

    @InjectMocks
    private EventController eventController; 

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    public void testAddEvent() {
        Event mockEvent = new Event();
        mockEvent.setName("Birthday Party");
        mockEvent.setEventDate(LocalDate.of(2025, 1, 1));

        when(eventService.addEvent(any(Event.class))).thenReturn(mockEvent);

        ResponseEntity<Event> response = eventController.addEvent(mockEvent);

        assertEquals(201, response.getStatusCodeValue()); 
        assertEquals(mockEvent, response.getBody()); 

        verify(eventService, times(1)).addEvent(any(Event.class));
    }
}
