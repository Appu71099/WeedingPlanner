package com.weddingplanner.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weddingplanner.exception.EventNotFoundException;
import com.weddingplanner.model.Event;
import com.weddingplanner.model.EventStatus;
import com.weddingplanner.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event addEvent(Event event) {
        if (event.getEventDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Event date cannot be in the past");
        }
        return eventRepository.save(event);
    }


    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event with ID " + id + " not found"));
    }

    public List<Event> getAllEvents(EventStatus status) {
        if (status != null) {
            return eventRepository.findByStatus(status);
        } else {
            return eventRepository.findAll();
        }
    }
}
