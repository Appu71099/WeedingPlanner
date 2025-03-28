package com.weddingplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weddingplanner.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
