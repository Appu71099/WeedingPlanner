package com.weddingplanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weddingplanner.model.Event;
import com.weddingplanner.model.EventStatus;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStatus(EventStatus status);
}
