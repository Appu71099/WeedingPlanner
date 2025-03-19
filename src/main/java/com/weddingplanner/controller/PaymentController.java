package com.weddingplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weddingplanner.model.Payment;
import com.weddingplanner.model.PaymentStatus;
import com.weddingplanner.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> recordPayment(@RequestBody Payment payment) {
        Payment createdPayment = paymentService.recordPayment(payment.getClient().getId(),payment.getAmount(),payment.getStatus());
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments(
        @RequestParam(required = false) PaymentStatus status) {
        List<Payment> payments = paymentService.getAllPayments(status);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}