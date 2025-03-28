package com.weddingplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.weddingplanner.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
