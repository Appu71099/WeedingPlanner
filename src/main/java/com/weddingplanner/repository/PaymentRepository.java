package com.weddingplanner.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weddingplanner.model.Client;
import com.weddingplanner.model.Payment;
import com.weddingplanner.model.PaymentStatus;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
    List<Payment> findByStatus(PaymentStatus status);

    Optional<Payment> findByClientAndStatus(Client client, PaymentStatus status);
}
