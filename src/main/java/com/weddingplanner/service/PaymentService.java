package com.weddingplanner.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weddingplanner.exception.ClientNotFoundException;
import com.weddingplanner.exception.PaymentNotFoundException;
import com.weddingplanner.model.Booking;
import com.weddingplanner.model.Client;
import com.weddingplanner.model.Payment;
import com.weddingplanner.model.PaymentStatus;
import com.weddingplanner.repository.ClientRepository;
import com.weddingplanner.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Payment recordPayment(Long clientId, BigDecimal amount, PaymentStatus status) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        Payment payment = new Payment();
        payment.setClient(client);
        payment.setAmount(amount);
        payment.setStatus(status);

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments(PaymentStatus status) {
        if (status == null) {
            return paymentRepository.findAll();
        } else {
            return paymentRepository.findByStatus(status);
        }
    }

    public void refundPayment(Booking booking) {
        Payment payment = paymentRepository.findByClientAndStatus(booking.getEvent().getClient(), PaymentStatus.COMPLETED)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found for client"));
        payment.setStatus(PaymentStatus.REFUNDED);
        paymentRepository.save(payment);
    }
}