package com.weddingplanner.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weddingplanner.exception.BookingNotFoundException;
import com.weddingplanner.exception.EventDateValidationException;
import com.weddingplanner.exception.EventNotFoundException;
import com.weddingplanner.exception.VendorNotAvailableException;
import com.weddingplanner.exception.VendorNotFoundException;
import com.weddingplanner.model.Booking;
import com.weddingplanner.model.Event;
import com.weddingplanner.model.Vendor;
import com.weddingplanner.repository.BookingRepository;
import com.weddingplanner.repository.EventRepository;
import com.weddingplanner.repository.VendorRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PaymentService paymentService;

    public Booking bookVendor(Long eventId, Long vendorId, BigDecimal price) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new VendorNotFoundException(vendorId));

        if (!vendor.isAvailable()) {
            throw new VendorNotAvailableException("Vendor is not available for this event.");
        }

        if (event.getEventDate().isBefore(LocalDate.now())) {
            throw new EventDateValidationException("Event date cannot be in the past.");
        }

        Booking booking = new Booking();
        booking.setEvent(event);
        booking.setVendor(vendor);
        booking.setBookingDate(LocalDate.now());
        booking.setPrice(price);

        Booking createdBooking = bookingRepository.save(booking);

        vendor.setAvailable(false);
        vendorRepository.save(vendor);

        return createdBooking;
    }

    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException(bookingId));

        paymentService.refundPayment(booking);

        Vendor vendor = booking.getVendor();
        vendor.setAvailable(true);
        vendorRepository.save(vendor);

        bookingRepository.delete(booking);
    }
}